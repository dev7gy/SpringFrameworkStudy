package com.dev7gy.core.scan.conflict;

import com.dev7gy.core.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

public class CoflictConfigTest {

    @Test
    void conflictConfig() {
        // No qualifying bean of type 'com.dev7gy.core.scan.conflict.ConflictBean' available: expected single matching bean but found 2: conflictBeanA,conflictBeanB
        Assertions.assertThrows(
                UnsatisfiedDependencyException.class,
                () -> new AnnotationConfigApplicationContext(AutoConflictAppConfig.class)
        );
    }

    @Test
    void autoConfigVSmanualConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ManualVSautoConfig.class);
    }

    @Configuration
    @ComponentScan
    public static class AutoConflictAppConfig { }

    @Configuration
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = Configuration.class
            )
            // AutoConflictAppConfig 를 제외하기 위한 filter 처리
    )
    public static class ManualVSautoConfig {
        /**
         * 수동 설정이 우선권을 가지게 된다.
         * Overriding bean definition for bean 'conflictBeanA' with a different definition:
         *
         */
        @Bean(name = "conflictBeanA")
        public ConflictBean conflictBeanA() {
            return new ConflictBeanA();
        }

        @Bean
        public ConflictService conflictService() {
            return new ConflictService(conflictBeanA());
        }

    }
}

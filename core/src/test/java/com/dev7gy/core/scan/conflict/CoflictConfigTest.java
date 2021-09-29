package com.dev7gy.core.scan.conflict;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.*;

public class CoflictConfigTest {
    @Configuration
    @ComponentScan(
            // ManualConflictAppConfig 를 제외하기 위한 filter 처리
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = Configuration.class
            )
    )
    public static class AutoConflictAppConfig { }

    @Test
    void conflictConfig() {
        // No qualifying bean of type 'com.dev7gy.core.scan.conflict.ConflictBean' available: expected single matching bean but found 2: conflictBeanA,conflictBeanB
        /**
         * 다른 테스트를 위해 충돌나는 ConflictBeanB 클래스 @Component 어노테이션 주석처리
         // ApplicationContext ac = new AnnotationConfigApplicationContext(AutoConflictAppConfig.class);
         // ConflictBeanB에서 @Component 주석 없애고 테스트 필요
         */
        Assertions.assertThrows(
                UnsatisfiedDependencyException.class,
                () -> new AnnotationConfigApplicationContext(AutoConflictAppConfig.class)
        );
    }

    @Configuration
    @ComponentScan(
            // AutoConflictAppConfig 를 제외하기 위한 filter 처리
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = Configuration.class
            )
    )
    public static class ManualConfig {
        /**
         * 수동 설정이 우선권을 가지게 된다.
         * Overriding bean definition for bean 'conflictBeanA' with a different definition:
         */
        @Bean(name = "conflictBeanA")
        public ConflictBean conflictBeanA() {
            System.out.println("Manual Config");
            return new ConflictBeanA();
        }

        @Bean
        public ConflictService conflictService() {
            System.out.println("Manual Config");
            return new ConflictService(conflictBeanA());
        }
    }

    @Test
    void manualConfigTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ManualConfig.class);
    }
}

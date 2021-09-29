package com.dev7gy.coreConflict.primary;

import com.dev7gy.coreConflict.ConflictBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

/*
 Bean 조회시 일부러 충돌을 내서 해결 방법 연습.
 */
public class pCoflictConfigTest {
    @Configuration
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = Configuration.class
            )
    )
    public static class AutoConflictAppConfig { }

    @Test
    void primaryConflict() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConflictAppConfig.class);
        ConflictBean conflictBeanA = ac.getBean("conflictBeanA", ConflictBean.class);
        ConflictBean conflictBeanB = ac.getBean("conflictBeanB", ConflictBean.class);
        assertThat(conflictBeanA).isInstanceOf(ConflictBeanA.class);
        assertThat(conflictBeanB).isInstanceOf(ConflictBeanB.class);
        ConflictService conflictService = ac.getBean("conflictService", ConflictService.class);
        assertThat(conflictService.conflictBean).isEqualTo(conflictBeanB);
    }
}


package com.dev7gy.coreConflict.allBeanDi;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

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
    void getConflictBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConflictAppConfig.class);
        ConflictService conflictService = ac.getBean("conflictService", ConflictService.class);
        assertThat(conflictService.getConflictBean("conflictBeanA")).isInstanceOf(ConflictBeanA.class);
        assertThat(conflictService.getConflictBean("conflictBeanB")).isInstanceOf(ConflictBeanB.class);
    }
}


package com.dev7gy.core.scan.lombok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokTest {
    LombokTestController controller;

    @BeforeEach
    void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(LombokTestAppConfig.class);
        controller = ac.getBean("lombokTestController", LombokTestController.class);
    }
    @Test
    void lombokTest() {
        LombokTestService lombokTestService = controller.getLombokTestService();
        String testName = "lombok success";
        lombokTestService.setName(testName);
        assertThat(lombokTestService.getName()).isEqualTo(testName);
    }

    @Configuration
    @ComponentScan(
            includeFilters = {
                    @ComponentScan.Filter(
                         type = FilterType.ASSIGNABLE_TYPE,
                         classes = {
                                 LombokTestService.class,
                                 LombokTestController.class
                         }
                    )
            }
    )
    public static class LombokTestAppConfig {
    }
}

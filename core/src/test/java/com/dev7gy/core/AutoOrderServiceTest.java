package com.dev7gy.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoOrderServiceTest {
    ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("모든 Bean 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames) {
            System.out.println("Name = " + beanDefinitionName + "Object = " + applicationContext.getBean(beanDefinitionName));
        }
    }

}

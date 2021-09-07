package com.dev7gy.core;

import com.dev7gy.core.AutoAppConfig;
import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.order.Order;
import com.dev7gy.core.order.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

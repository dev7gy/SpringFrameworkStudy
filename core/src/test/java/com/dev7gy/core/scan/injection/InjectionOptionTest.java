package com.dev7gy.core.scan.injection;

import com.dev7gy.core.scan.injection.service.ServiceForDI;
import com.dev7gy.core.scan.injection.service.ServiceImplForDI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InjectionOptionTest {

    ServiceForDI service;
    @BeforeEach
    void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(InjectionAutoAppConfig.class);
        service = ac.getBean("serviceImplForDI", ServiceImplForDI.class);
    }
    @Test
    void setTest_1() {
        service.getNoSpringBean();
    }

    @Test
    void setTest_2() {
        service.getNoSpringBean();
    }

    @Test
    void setTest_3() {
        service.getNoSpringBean();
    }
}

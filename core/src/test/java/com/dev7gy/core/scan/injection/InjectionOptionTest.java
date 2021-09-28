package com.dev7gy.core.scan.injection;

import com.dev7gy.core.scan.injection.service.ServiceForDI;
import com.dev7gy.core.scan.injection.service.ServiceImplForDI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;

public class InjectionOptionTest {

    ServiceForDI service;
    @BeforeEach
    void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(InjectionAutoAppConfig.class);
        service = ac.getBean("serviceImplForDI", ServiceImplForDI.class);
    }
    @Test
    void getNoSpringBeanTest1() {
        assertThat(service.getNoSpringBean()).isNull();
    }

    @Test
    void getNoSpringBeanTest2() {
        assertThat(service.getOptionalNoSpringBean()).isEqualTo(Optional.empty());
    }
}

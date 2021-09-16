package com.dev7gy.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScaen() {
        // 스프링 컨테이너를 ComponentFilterAppConfig.class 바탕으로 띄운다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        IncludeBean includeBean = ac.getBean("includeBean", IncludeBean.class);
        assertThat(includeBean).isNotNull();

        try {
            ExcludeBean excludeBean = ac.getBean("excludeBean", ExcludeBean.class);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }

    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}

package com.dev7gy.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {
    @Test
    public void FindSingletonBean() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(SingleTonBean.class);
        SingleTonBean singleTonBean1 = ac.getBean(SingleTonBean.class);
        SingleTonBean singleTonBean2 = ac.getBean(SingleTonBean.class);
        assertThat(singleTonBean1).isSameAs(singleTonBean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingleTonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingleTonBean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingleTonBean - destroy");
        }
    }
}

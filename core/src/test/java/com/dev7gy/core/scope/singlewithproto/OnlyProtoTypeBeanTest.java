package com.dev7gy.core.scope.singlewithproto;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class OnlyProtoTypeBeanTest {

    @Test
    void GetProtoType() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(OnlyProtoTypeBean.class);
        OnlyProtoTypeBean onlyProtoTypeBean1 = ac.getBean(OnlyProtoTypeBean.class);
        onlyProtoTypeBean1.increaseCount();
        assertThat(onlyProtoTypeBean1.getCount()).isEqualTo(1);

        OnlyProtoTypeBean onlyProtoTypeBean2 = ac.getBean(OnlyProtoTypeBean.class);
        onlyProtoTypeBean2.increaseCount();
        assertThat(onlyProtoTypeBean2.getCount()).isEqualTo(1);
    }

    @Scope("prototype")
    static class OnlyProtoTypeBean {
        private int count = 0;

        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("OnlyProtoTypeBean - init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("OnlyProtoTypeBean - destroy");
        }
    }
}

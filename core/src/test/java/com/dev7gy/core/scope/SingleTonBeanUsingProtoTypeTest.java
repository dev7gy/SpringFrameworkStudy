package com.dev7gy.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonBeanUsingProtoTypeTest {
    @Test
    void WrongSingletonBeanUsingProtoType() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WrongSingleTonBean.class, WrongProtoTypeBean.class);
        WrongSingleTonBean wrongSingleTonBean1 = ac.getBean(WrongSingleTonBean.class);
        int count1 = wrongSingleTonBean1.logic();
        assertThat(count1).isEqualTo(1);

        WrongSingleTonBean wrongSingleTonBean2 = ac.getBean(WrongSingleTonBean.class);
        int count2 = wrongSingleTonBean2.logic();
        /**
         * WrontSingleTonBean은 SingleTon이기 때문에 wrongSingleTonBean1 == wrongSingleTonBean2
         * 멤버변수로 사용하고 있는 wrongProtoTypeBean도 결국 같은 값
         * 그래서 2번 시킨값이 나오게 됨.
         *
         */
        assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class WrongSingleTonBean {
        private final WrongProtoTypeBean wrongProtoTypeBean;

        @Autowired
        public WrongSingleTonBean(WrongProtoTypeBean wrongProtoTypeBean) {
            this.wrongProtoTypeBean = wrongProtoTypeBean;
        }

        public int logic() {
            wrongProtoTypeBean.increaseCount();
            int count = wrongProtoTypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class WrongProtoTypeBean {
        private int count = 0;
        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("WrongProtoTypeBean Bean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("WrongProtoTypeBean Bean - destroy");
        }
    }
}

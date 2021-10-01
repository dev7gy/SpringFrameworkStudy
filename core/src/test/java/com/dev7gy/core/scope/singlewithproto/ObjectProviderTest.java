package com.dev7gy.core.scope.singlewithproto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectProviderTest {
    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FixedSingleTon2.class, FixedProtoType2.class);
        FixedSingleTon2 fixedSingleTon21 = ac.getBean(FixedSingleTon2.class);
        int count1 = fixedSingleTon21.logic();
        assertThat(count1).isEqualTo(1);

        FixedSingleTon2 fixedSingleTon22 = ac.getBean(FixedSingleTon2.class);
        int count2 = fixedSingleTon22.logic();
        assertThat(count1).isEqualTo(1);
    }

    @Scope("prototype")
    static class FixedProtoType2 {
        private int count;
        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("FixedProtoType2 Bean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("FixedProtoType2 Bean - destroy");
        }
    }

    @Scope("singleton")
    static class FixedSingleTon2 {
        /*
        ObjectProvider 사용하기
         */
        @Autowired
        private ObjectProvider<FixedProtoType2> fixedProtoType2Provider;

        public int logic() {
            /**
             *
             */
            FixedProtoType2 fixedProtoType = fixedProtoType2Provider.getObject();
            fixedProtoType.increaseCount();

            int count = fixedProtoType.getCount();
            return count;
        }
    }
}

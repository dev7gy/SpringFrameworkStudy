package com.dev7gy.core.scope.singlewithproto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class JSR330ProviderTest {
    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ObjectProviderTest.FixedSingleTon2.class, ObjectProviderTest.FixedProtoType2.class);
        ObjectProviderTest.FixedSingleTon2 fixedSingleTon21 = ac.getBean(ObjectProviderTest.FixedSingleTon2.class);
        int count1 = fixedSingleTon21.logic();
        assertThat(count1).isEqualTo(1);

        ObjectProviderTest.FixedSingleTon2 fixedSingleTon22 = ac.getBean(ObjectProviderTest.FixedSingleTon2.class);
        int count2 = fixedSingleTon22.logic();
        assertThat(count1).isEqualTo(1);
    }

    @Scope("prototype")
    static class FixedProtoType3 {
        private int count;
        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("FixedProtoType3 Bean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("FixedProtoType3 Bean - destroy");
        }
    }

    @Scope("singleton")
    static class FixedSingleTon3 {
        /**
         * JSR330 Provider 사용하기
         * build.gradle에 implementation 'javax.inject:javax.inject:1' 추가
         */
        @Autowired
        private Provider<FixedProtoType3> fixedProtoType3Provider;

        public int logic() {
            FixedProtoType3 fixedProtoType = fixedProtoType3Provider.get();
            fixedProtoType.increaseCount();

            int count = fixedProtoType.getCount();
            return count;
        }
    }
}

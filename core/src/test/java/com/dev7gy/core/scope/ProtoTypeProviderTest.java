package com.dev7gy.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeProviderTest {
    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FixedSingleTon1.class, FixedProtoType1.class);
        FixedSingleTon1 fixedSingleTon1 = ac.getBean(FixedSingleTon1.class);
        int count1 = fixedSingleTon1.logic();
        assertThat(count1).isEqualTo(1);

        FixedSingleTon1 fixedSingleTon12 = ac.getBean(FixedSingleTon1.class);
        int count2 = fixedSingleTon12.logic();
        assertThat(count1).isEqualTo(1);
    }

    @Scope("prototype")
    static class FixedProtoType1 {
        private int count;
        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("FixedProtoType1 Bean - init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("FixedProtoType1 Bean - destroy");
        }
    }

    @Scope("singleton")
    static class FixedSingleTon1 {
        /*
        멤버 변수로 스프링 컨테이너를 두고 프로토 타입을 사용할때마다 요청하기
         */
        @Autowired
        private ApplicationContext ac;

        public int logic() {
            /**
             * 이 부분에서 항상 새로운 FixedProtoType1 빈이 생성된다.
             * -> 의존관계를 외부에서 주입 받는 것이 아니라 직접 의존 관계를 찾는 것을 Dependency LookUP == 의존관계 조회 라 한다.
             *
             * -> 좋은 방법 같아보이지만, 스프링 컨테이너에 종속적인 코드가 된다.
             */
            FixedProtoType1 fixedProtoType1 = ac.getBean(FixedProtoType1.class);
            fixedProtoType1.increaseCount();

            int count = fixedProtoType1.getCount();
            return count;
        }
    }
}

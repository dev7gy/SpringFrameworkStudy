package com.dev7gy.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Simple Spring Bean Life Cycle>
 * 객체 생성 -> 의존관계 주입
 *
 * !중요! Spring Bean 초기화 시점, 소멸 시점을 어떻게 알 수 있을까?
 *  > 3가지 방법
 *  1. 인터페이스(InitializingBean, DisposableBean)
 *  2. 설정 정보에 초기화 메서드, 종료 매서드 지정
 *  3. @PostConstructor, @PreDestroy 어노테이션 지정
 *
 * !중요! 객체의 생성과 초기화(무거운 작업일 경우)를 분리하자
 * <Spring Bean Event Life Cycle>
 *     - Single Tone
 *      Spring Container 생성 -> Spring Bean 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 
 *      -> 소멸전 콜백 -> 스프링 종료
 */

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // 스프링 컨테이너를 close하기 위해서는 ApplicationContext 대신 다른 class를 사용해야 함.
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /*
        @PostConstructor, @PreDestroy를 사용하면 직접 지정해줄 필요 없음
        -> 외부 라이브러리 사용시엔 @Bean(initMethod = "init", destroyMethod = "close") 방법을 사용하자
         */
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://dev7gy.spring.practice");
            return networkClient;
        }
    }
}

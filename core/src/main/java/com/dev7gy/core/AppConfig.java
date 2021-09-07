package com.dev7gy.core;

import com.dev7gy.core.discount.DiscountPolicy;
import com.dev7gy.core.discount.FixDiscountPolicy;
import com.dev7gy.core.member.repository.MemberRepository;
import com.dev7gy.core.member.repository.MemoryMemberRepository;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import com.dev7gy.core.order.example.OrderService;
import com.dev7gy.core.order.example.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 구현 클래스들의 객체를 생성하고 연결시켜주는 기능을 할 클래스 -> 스프링에서 IoC 컨테이너(DI 컨테이너)에 해당된다.
 *
 * <역할>
 * - 실제 동작에 필요한 구현 객체를 생성한다.
 * - 생성자를 통해서 연결시켜준다.
 */
@Configuration
public class AppConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {

        DiscountPolicy showBeanInstance = new FixDiscountPolicy();
        // FixDiscountDolicy@2625 와 같은 식으로 빈 객체가 만들어진다.
        return showBeanInstance;
        // return new RateDiscountPolicy();
        /**
         * AppConfig 영역에서만 상황에 맞게 조합하면 된다.
         */
    }

    @Bean(name="memberService_changedName")
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService_changed() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

/**
 * 기억해야 할 개념
 * IoC(Inversion of Control)
 * DI(Dependency Injection) :
 * 실행시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입이라고 한다.
 *
 * !ApplicationContext! 는 스프링 컨테이너 - 아래와 같이 사용하게 됨.
 *  ApplicationContext applicationContext= new AnnotationConfigApplicationContext(AppConfig.class);
 *  memberService=applicationContext.getBean("memberService",MemberService.class);
 *  getBean(빈이름, 클래스)
 *  빈이름은 따로 name="이름"해서 지정하지 않으면 함수명이 빈이름이 된다.
 *
 */

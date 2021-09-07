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
@Configuration // @Configuraion은 싱글톤을 보장
public class AppConfig {
    /**
     *
     * memberRepository() 함수를 3번 호출하면서 new가 3번있는데 어떻게 싱글톤을 유지할 수 있을까.
     * < @Configuration에 의해 진행될 CGLIB 내부 동작을 추측 >
     * Name = appConfigObject
     * = com.dev7gy.core.AppConfig$$EnhancerBySpringCGLIB$$
     * AppConfig는 CGLIB 기술을 사용해서 바이트 코드를 조작한다. 결국 실제 인스턴스가 아니라
     * CGLIB를 통해 만들어진 클래스가 싱글톤으로 연결된다.
     * -> 결국 내부적으로는 이미 스프링 컨테이너에 만들어진 싱글톤 빈이면 만들지 않는 것이다.
     */
    @Bean // @Bean은 싱글톤을 보장하지 않음.
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

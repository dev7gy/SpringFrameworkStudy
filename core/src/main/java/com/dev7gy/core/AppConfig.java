package com.dev7gy.core;

import com.dev7gy.core.discount.DiscountPolicy;
import com.dev7gy.core.discount.FixDiscountPolicy;
import com.dev7gy.core.member.repository.MemberRepository;
import com.dev7gy.core.member.repository.MemoryMemberRepository;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import com.dev7gy.core.order.example.OrderService;
import com.dev7gy.core.order.example.OrderServiceImpl;

/**
 * 구현 클래스들의 객체를 생성하고 연결시켜주는 기능을 할 클래스
 *
 * <역할>
 * - 실제 동작에 필요한 구현 객체를 생성한다.
 * - 생성자를 통해서 연결시켜준다.
 */
public class AppConfig {
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        // return new RateDiscountPolicy();
        /**
         * AppConfig 영역에서만 상황에 맞게 조합하면 된다.
         */
    }


    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}

/**
 * 기억해야 할 개념
 * IoC(Inversion of Control)
 * DI(Dependency Injection)
 */

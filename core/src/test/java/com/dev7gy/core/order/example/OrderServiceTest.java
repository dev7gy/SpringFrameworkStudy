package com.dev7gy.core.order.example;

import com.dev7gy.core.AppConfig;
import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import com.dev7gy.core.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach() {
        /** !Spring 적용 전
         * AppConfig appConfig = new AppConfig();
         * this.memberService = appConfig.memberService();
         * this.orderService = appConfig.orderService();
         */
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService_changedName", MemberService.class);
        orderService = applicationContext.getBean("orderService_changed", OrderService.class);
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

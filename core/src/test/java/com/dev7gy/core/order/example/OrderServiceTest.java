package com.dev7gy.core.order.example;

import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import com.dev7gy.core.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

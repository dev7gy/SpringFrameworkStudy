package com.dev7gy.core.discount.example;

import com.dev7gy.core.discount.RateDiscountPolicy;
import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10% 할인 적용 필요")
    void vipDiscount() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP는 10% 할인 적용 필요")
    void basicDicount() {
        //given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}

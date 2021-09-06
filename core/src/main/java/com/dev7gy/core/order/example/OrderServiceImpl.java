package com.dev7gy.core.order.example;

import com.dev7gy.core.discount.DiscountPolicy;
import com.dev7gy.core.discount.FixDiscountPolicy;
import com.dev7gy.core.discount.RateDiscountPolicy;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.repository.MemberRepository;
import com.dev7gy.core.member.repository.MemoryMemberRepository;
import com.dev7gy.core.order.Order;

public class OrderServiceImpl implements OrderService {
    /**
     * 메모리 회원 리포지토리와, 고정 금액 할인 정책을 구현체로 생성한다.
     * -> 이렇게 되면 할인정책이 바뀌었을땐, orderServicImpl은 추상화에 의존한게 아니라,
     * Discount 정책이 바뀔때마다 new할 구현체를 계속 수정해야 한다.
     * 결국, DIP(추상화에 의존하려면) 가 지켜지려면, 코드는
     * private final DiscountPolicy; 이렇게 되야 한다. 여기서 new 하는 것이 아니라.
     * 그렇기 때문에 AppConfig가 필요하게 된다.
     * */
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /**
     AppConfig 적용 후 코드
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

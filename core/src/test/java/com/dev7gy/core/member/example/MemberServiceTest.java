package com.dev7gy.core.member.example;

import com.dev7gy.core.AppConfig;
import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {

    MemberService memberService;
    @BeforeEach
    public void beforeEach() {
        /**
         * !중요! 스프링 적용 전
         * AppConfig appConfig = new AppConfig();
         * this.memberService = appConfig.memberService();
         *
         */
        /**
         * ApplicationContext 는 스프링 컨테이너
         */
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService_changedName", MemberService.class);

    }
    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

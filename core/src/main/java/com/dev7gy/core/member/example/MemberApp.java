package com.dev7gy.core.member.example;

import com.dev7gy.core.member.Grade;
import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.repository.MemberRepository;
import com.dev7gy.core.member.repository.MemoryMemberRepository;
import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;

/**
 *  !중요! 좋지 않은 테스트 방법
 *
 *  MemberServiceTest 클래스처럼 테스트를 작성할 것.
 */
public class MemberApp {
    public static void main(String[] args) {
        MemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

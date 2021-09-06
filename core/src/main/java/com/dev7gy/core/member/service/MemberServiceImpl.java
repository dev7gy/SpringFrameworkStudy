package com.dev7gy.core.member.service;

import com.dev7gy.core.member.Member;
import com.dev7gy.core.member.repository.MemberRepository;
import com.dev7gy.core.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

    /**
     * new로 특정 클래스의 Repository를 생성하고 있으므로 문제가 있다.
     */
    private final MemberRepository memberRepository = new
            MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

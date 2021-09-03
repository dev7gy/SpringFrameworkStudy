package com.dev7gy.core.member.service;

import com.dev7gy.core.member.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}

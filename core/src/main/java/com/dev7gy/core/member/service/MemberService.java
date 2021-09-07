package com.dev7gy.core.member.service;

import com.dev7gy.core.member.Member;
import org.springframework.stereotype.Component;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}

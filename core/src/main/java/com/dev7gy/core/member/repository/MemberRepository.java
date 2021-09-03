package com.dev7gy.core.member.repository;

import com.dev7gy.core.member.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long meberId);
}

package com.dev7gy.core.member.repository;

import com.dev7gy.core.member.Member;
import org.springframework.stereotype.Component;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long meberId);
}

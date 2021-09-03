package com.dev7gy.core.member.repository;

import com.dev7gy.core.member.Member;

import java.util.HashMap;
import java.util.Map;

/**
Database가 아직 확정되기 전에 메모리 회원 저장소를 통해 개발을 진행한다.
 MemberRepository 인터페이스만 미리 설계
 HashMap은 동시성 이슈가 발생할 수 있다. -> ConcurrentHashMap을 사용하자.
 */
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long meberId) {
        return store.get(meberId);
    }
}

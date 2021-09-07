package com.dev7gy.core.singleton;

import com.dev7gy.core.AppConfig;
import com.dev7gy.core.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class singletonServiceTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        /**
         * 참조값이 다른 것을 확인
         * System.out.println("memberService1 = " + memberService1);
         * System.out.println("memberService2 = " + memberService2);
         * memberService1 != memberService2
         * memberService1 = com.dev7gy.core.member.service.MemberServiceImpl@54d9d12d
         * memberService2 = com.dev7gy.core.member.service.MemberServiceImpl@38425407
         */
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        // private으로 생성자를 막아두었음. 컴파일 오류가 발생

        /**
         * 조회: 호출할 때 마다 같은 객체를 반환
         * 참조값이 같은 것을 확인
         * System.out.println("singletonService1 = " + singletonService1);
         * System.out.println("singletonService2 = " + singletonService2);
         * singletonService1 == singletonService2
         * singletonService1 = com.dev7gy.core.singleton.SingletonService@45752059
         * singletonService2 = com.dev7gy.core.singleton.SingletonService@45752059
         */
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
        singletonService1.logic();
    }
}

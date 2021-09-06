package com.dev7gy.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
/**
 * - 비즈니스 요구사항과 설계
 * 1. 회원
 * 	회원을 가입하고 조회할 수 있다.
 * 	회원은 일반과 VIP 두 가지 등급이 있다.
 * 	회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
 * 2. 주문과 할인 정책
 * 	회원은 상품을 주문할 수 있다.
 * 	회원 등급에 따라 할인 정책을 적용할 수 있다.
 * 	할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수
 * 	있다.)
 * 	할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을
 * 	미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)
 *
 * 	인터페이스를 만들고 구현채를 언제든지 갈아끼울 수 있도록 설계하면 된다.
 */

/*
역할과 구현을 분리해야 함.
1. 주문 생성: 클라이언트는 주문 서비스에 주문 생성을 요청한다.
2. 회원 조회: 할인을 위해서는 회원 등급이 필요하다. 그래서 주문 서비스는 회원 저장소에서 회원을 조회
한다.
3. 할인 적용: 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임한다.
4. 주문 결과 반환: 주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다
 */
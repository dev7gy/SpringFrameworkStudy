package com.dev7gy.core;

import com.dev7gy.core.member.service.MemberService;
import com.dev7gy.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스프링 컨테이너의 기능을 확인하기 위한 테스트 구성
 */
@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	// AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
	@Test
	@DisplayName("모든 Bean 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDefinitionName : beanDefinitionNames) {
			System.out.println("Name = " + beanDefinitionName + "Object = " + ac.getBean(beanDefinitionName));
		}
	}

	@Test
	@DisplayName("개발자가 등록한 Bean 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();

		for(String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition bd = ac.getBeanDefinition(beanDefinitionName);
			if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("Name = " + beanDefinitionName + "Object = " + ac.getBean(beanDefinitionName));
			}
		}
	}

	@Test
	@DisplayName("스프링이 내부에서 사용하는 Bean 출력하기")
	void findInfraBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();

		for(String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition bd = ac.getBeanDefinition(beanDefinitionName);
			if (bd.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
				System.out.println("Name = " + beanDefinitionName + "Object = " + ac.getBean(beanDefinitionName));
			}
		}
	}

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService",
				MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	@Test
	@DisplayName("이름 없이 타입만으로 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {
		MemberServiceImpl memberService = ac.getBean("memberService",
				MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	@Test
	@DisplayName("빈 이름으로 조회X")
	void findBeanByNameX() {
		//ac.getBean("xxxxx", MemberService.class);
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
				ac.getBean("xxxxx", MemberService.class));
	}
}
/**
 * BeanFactory : 스프링 컨테이너의 최상위 인터페이스
 * 스프링 빈을 관리하고 조회하는 역할을 담당한다.
 *
 * ApplicationContext -> ListableBeanFactory -> BeanFactory
 * ApplicationContext가 BeanFactory를 상속받은건데 둘의 차이는 뭘까?
 *  - 메세지 소스를 활용한 국제화 기능: 한국권, 영어권에 맞춰서 출력
 *  - 환경 변수: 로컬, 개발, 운영 등을 구분해서 처리
 *  - 애플리케이션 이벤트: 이벤트를 발행하고 구독하는 모델을 편리하게 지원
 *  - 편리한 리소스 조회: 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회
 *
 * BeanDefinition 이란?
 * 스프링이 다양한 형태의 설정정보(xml, annotaion..)를 BeanDefinition으로 추상화해서 사용한다.
 * !스프링 빈 설정 메타정보 강의 추가로 복습하기! 2021.09.07
 */


# 스프링 핵심원리 기본편 - 김영한 강사님.

## SOLID
### - SRP: 단일 책임 원칙
- 한 클래스는 하나의 책임만 가져야 한다. 
```
-> 하나의 책임은 너무 모호함 
-> !중요한 기준은 변경! 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것
```
    
### - OCP: 개방 폐쇄 원칙
- 소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다.
```
다형성을 활용한다. 
MemberRepository mR = new MemoryMemberRepository();
MemberRepository mR = new JdbcMemberRepository();
하지만 다형성을 활용하기 위해 인터페이스를 사용해도 위 코드처럼 상황에 따라 코드가 바뀌어야 한다.
-> 분명 다형성을 사용했지만, OCP 원칙을 지킬 수 없다.
--> 이 문제점을 해결하기 위해 DI 와 IOC 컨테이너가 필요하다. 
```
### - LSP: 리스코프 치환 원칙
- 인터페이스 규약을 지켜서 하위 클래스를 만들어야 한다.
``` 
-> 전진이라는 함수가 있다면 하위 클래스도 반드시 전진해야지 후진하거나 하면 안됨.
```
### - ISP: 인터페이스 분리 원칙
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
### - DIP: 의존관계 역전 원칙
- 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
```
역할에 의존하게 해야 한다
```
## SpringContainer
### 스프링 빈 저장소
|빈 이름|빈 객체|
|------|------|
|memberServiceImpl|memberServiceImpl@x01|

## 주요 어노테이션
- @Bean
- @ComponentScan
   - 탐색 위치를 basePakcages= "com.dev7gy.core" 와 같이 지정해줄 수 있다.
   - 지정하지 않으면, @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
   - @SpringBootApplication 안에도 @ComponentScan이 있다. -> Annotation에는 상속 관계가 없음, 특정 Annotation을 내부적으로 가지고 있는 것은 Spring의 기능
   - 컴포넌트 스캔 기본 대상
    - @Component
    - @Controller
    - @Service
    - @Repository
    - @Configuration
- @AutoWired

## 다양한 의존관계 주입 방법
- 생성자 주입: 불변, 필수 의존관계에서 주입, 스프링 빈에 대해서 생성자가 딱 1개만 있으면, @Autowired를 생략해도 자동 주입이 된다.
- 수정자(setter) 주입: 선택, 변경 가능성이 있는 의존관계에서 사용
```
추가 공부항목: 자바빈 프로퍼티 규약
```
- 필드 주입: 필드에 바로 주입, 특별한 경우가 아니면 사용하지 말자.
- 일반 매서드 주입: 일반 매서드를 통해서 주입, 일반적으로 잘 사용하지 않음.

## 의존관계 주입 옵션 처리
- 사전 조건: TestBean은 스프링 빈이 아님.
- 자동 주입할 대상이 없으면 수정자 매서드 자체를 호출하지 않는 경우
```
@Autowired(required = false)
public void setNoBean(TestBean tb) {
}
```
- 자동 주입할 대상이 없을때 null이 입력되는 경우
```
@Autowired
public void setNoBean(@Nullable TestBean tb) {
}
```
- 자동 주입할 대상이 없으면 Optional.empty가 입력되는 경우
```
@Autowired(required = false)
public void setNoBean(Optinal<TestBean> tb) {
}
```
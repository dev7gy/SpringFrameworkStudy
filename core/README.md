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
   - 컴포넌트 스캔 filter
    - ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
        ```
          @Configuration
          @ComponentScan(
                  includeFilters = @ComponentScan.Filter(
                          type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                  excludeFilters = @ComponentScan.Filter(
                          type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
          )
        ```
    - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
        ```
          @Configuration
          @ComponentScan(
                  includeFilters = {
                          @ComponentScan.Filter(
                               type = FilterType.ASSIGNABLE_TYPE,
                               classes = {
                                       LombokTestService.class,
                                       LombokTestController.class
                               }
                          )
                  }
          )
        ```
    - ASPECTJ: AspectJ 패턴 사용
    - REGEX: 정규 표현식
    - CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
- @AutoWired

## 다양한 의존관계 주입 방법
- 생성자 주입: 불변, 필수 의존관계에서 주입, 스프링 빈에 대해서 생성자가 딱 1개만 있으면, @Autowired를 생략해도 자동 주입이 된다.
    - !중요! 장점 생성자 주입에서는 final 키워드를 사용할 수 있다. - 생성자에서 값이 설정되지 않은 경우에 컴파일 오류를 발생한다
    - Lombok을 이용하면 더욱더 간편하게 사용 가능, @RequiredArgsConstructor @Getter, @Setter
- 수정자(setter) 주입: 선택, 변경 가능성이 있는 의존관계에서 사용
    ```
    추가 공부항목: 자바빈 프로퍼티 규약
    ```
- 필드 주입: 필드에 바로 주입, 특별한 경우가 아니면 사용하지 말자.
- 일반 매서드 주입: 일반 매서드를 통해서 주입, 일반적으로 잘 사용하지 않음.

### 의존관계 주입 옵션 처리
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
### 의존관계 주입시 충돌 나는 경우 해결법
- @Configuraion에 직접 넣어줄 Bean 직접 정의
- @Qualifire 사용해서 별칭 달아주기 -> Annotaion 직접 만들어서 적용하기
- @Primary 사용하기  
- 충돌나는 Bean을 여러개 주입 받는 방법, Map<key, 충돌Bean.class>, List<충돌Bean.class> 로 주입한 다음에 key값에 따라 동작할 수 있게 구성

#### 스프링 컨테이서 생성하면서 스프링 빈 등록하기
```
스프링 컨테이너를 생성하면서 Bean 2개를 등록한다.
new AnnotationConfigApplicationContext(스프링Bean1.class, 스프링Bean2.class);
```

#### 언제 수동 Bean 등록을 사용해야 할까?
- 직접 만든 기술 Ligic Bean(AOP), 다형성을 적극 활용하는 Bean일 경우

## Bean LifeCycle CallBack
- 스프링에서는 App 시작 시점에 필요한 연결을 미리 해두고, App 종료 시점에 연결을 종료하는 작업을 하려고 한다면, 객체의 초기화와 종료작업을 어떻게 진행할 것인가.
    - 1. 인터페이스(InitializingBean, DisposableBean)
        ```
      // 단점 Spring 전용 Interface에 의존하게 됨. - 거의 사용하지 않음.
      public class NetworkClient implements InitializingBean, DisposableBean
        ```
    - 2. 설정 정보에 초기화 메서드, 종료 매서드 지정
        ```
      /*
      !추가 공부 필요!종료 메서드 추론
      @Bean의 destroyMethod 속성에는 아주 특별한 기능
      */
      // NerworkClient.java
      public class NetworkClient {
        public void init() {}
        public void close() {}
      }
      
      // Configuration.java
      static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() { return }
      }
        ```
    - 3. @PostConstructor, @PreDestroy 어노테이션 지정

## Bean Scope
- Single Tone: 기본 Scope, Spring Container의 시작과 종료까지 유지
- Proto Type: Spring Container는 프로토타입 Bean의 생성과 의존관계 주입까지만 관여, 더는 관리하지 않는 매우 짧은 범위의 Scope
- Web Type: 
    - request: Web 요청이 들어오고 나갈때 까지 유지되는 Scope
    - session: Web Session이 생성되고 종료될 때까지 유지되는 Scope
    - application: Web의 서블릿 컨텍스트와 같은 범위로 유지되는 Scope
 ```
// 컴포넌트 스캔 이용시
@Scope("prototype")
@Component
public class ProtoBean {}

// 수동 등록 
@Scope("prototype")
@Bean
ProtoBean registerProtoBean() {
    return new ProtoBean();
}
```
### proto type 사용 방법
- Spring Bean 내부에 스프링 컨테이너를 두고 요청하는 방법
- Provider를 사용하는 것.
    - ObjectFactory, ObjectProvider
        ```
      public class SingletonBean {
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            }
      }
        ```
    - JSR-330 Provider
        ```
      public class SingletonBean {
        @Autowired
        private Provider<PrototypeBean> provider;
        public int logic() {
            PrototypeBean prototypeBean = provider.get()
            }
      }
        ```
### Web Scope
- 웹 환경에서만 동작, 해당 scope 종료시점까지 관리 대상
- 종류
    - request
    - session
    - application
    - websocket
    
### Web Scope를 주입해서 사용하기
- ObjectProvider를 이용하여 request Scope 빈의 생성을 지연하기
```
!중요! controller와 service 모두 동일한 request에 대해서는 같은 CustomLogger Bean을 사용한다.
public class LogCheckController {
private final ObjectProvider<CustomLogger> customLoggerProvider;
}

@Component
@Scope(value = "request")
public class CustomLogger {}
```
package com.dev7gy.core.singleton;

/**
 * < 싱글톤 패턴 문제점 >
 * - 싱글톤 패턴을 구현하는 코드 자체가 많아짐 -> private 생성자, static 등등
 * - 의존관계상 클라이언트가 구체 클래스에 의존한다 - DIP(추상화에 의존)를 위반
 * - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
 * - 테스트하기 어렵다.
 * - 내부 속성을 변경하거나 초기화 하기 어렵다.
 * - private 생성자로 자식 클래스를 만들기 어렵다.
 * - 결론적으로 유연성이 떨어진다.
 * - 안티패턴으로 불리기도 한다
 * -> 위 문제를 해결하기 위해 싱글톤 컨테이너를 사용한다.
 *
 *  !중요! 스프링 컨테이너는 싱글톤 컨테이너 역할을 함.
 *
 *  < 싱글톤 방식의 주의점 >
 *  객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은
 *  여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에
 *  싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
 *  무상태(stateless)로 설계해야 한다!
 * - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
 * - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
 * - 가급적 읽기만 가능해야 한다.
 * - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
 */
public class SingletonService {
    // static final 을 이용하여 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();
    // 생성자를 private으로 선언해서 외부에서 new 키워드를 통해 객체 생성을 못하게 막음.
    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

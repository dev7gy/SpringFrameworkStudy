# [JPA](https://www.inflearn.com/course/ORM-JPA-Basic/dashboard)

## 1. SQL 중심적인 개발의 문제점

### 1-a. 객체와 관계형 데이터베이스의 차이
- 상속
- 연관관계
- 데이터 타입
- 데이터 식별 방법
```
!중요! 패러다임의 불일치 발생 -> 
객체 컬렉션에서 데이터를 조회하는 것처럼 DB에서 조회할 수는 없을까?라는 고민
```

## 2. JPA란?
- Java Persisence API
- 자바 진영의 ORM 표준 기술
- JPA는 인터페이스의 모음 <- Hibernate, EclipseLink, DataNucleus등의 구현체가 존재
- 성능 최적화 기능 제공


### 2-a. ORM이란?
- Object-relational mapping
- ORM 프레임워크가 중간에서 객체와 DB를 매핑

### 2-b. JPA 특장점
- 지연 로딩 & 즉시 로딩:
```
지연 로딩: 객체가 실제 사용될 때 로딩
즉시 로딩: JOIN SQL로 한번에 연관된 객체까지 미리 조회
```


## 3. JPA 설정 파일 resources/META-INF/persistence.xml
- Database Dialect 지원
- javax, hibernate 차이 javax는 표준으로 된 것, hibernate라고 되어 있는건 전용 옵션.

### 3-a. JPA 구동 방식
- 1.persistence.xml로 부터 설정 정보를 읽어들인다.
- 2.EntityManagerFactory를 만든다.
- 3.EMF를 통해 EntityManager를 만든다. 

### 3-b. 기본 실습에서 !중요! 개념
- EntityManagerFactory를 만들 때에는 persistence.xml 파일에 정의해둔 persistence-unit 의 이름을 넣어줘야 한다.
- JPA의 모든 데이터 변경은 transaction 안에서 실행해야 한다.
- 데이터 crud가 끝난 후에는 반드시 EntityManager를 반환해줘야 한다. entityManager.close();
- EntityManager는 DB 사용 요청이 있을 때마다 생성되어서 사용하는 것이므로 쓰레드 간 공유를 하면 !절대! 안된다.
- 애플리케이션이 종료 될 때 EntityManagerFactory도 반환해줘야 한다.

## *JPA에서 가장 중요한 2가지* 
- 객체와 관계형 데이터베이스 매핑하기 -> 정적인 개념
- 영속성 컨텍스트 -> 내부 동작

## 4. 영속성 컨텍스트(Persistence Context)
- 엔티티를 영구 저장하는 환경
- 논리적인 개념
```
EntityManager.persist(entity); 를 앞에서는 데이터베이스에 entity를 저장한다고 배웠지만, 실제로는 entity를 영속성 컨텍스트에 저장하는 것이다.
```

### 4-a. Entity의 생명 주기
- 비영속(new/transient) 
- 영속(managed)
- 준영속(detached)
- 삭제(removed)
```
Member member = new Member();
entityManager.persist(member);
entityManager.detach(member);
entityManager.remove(member);
```

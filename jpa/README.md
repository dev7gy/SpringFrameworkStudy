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
- 비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속(managed): 영속성 컨텍스트에 관리되는 상태
- 준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed): 객체를 삭제
```
Member member = new Member();
entityManager.persist(member);
entityManager.detach(member);
entityManager.remove(member);
```

## 4-b. 1차 캐시, 쓰기 지연 SQL 저장소
- 영속성 컨텍스트에는 내부에 1차 캐시가 아래와 같은 구조로 존재한다.
- 1차 캐시는 여러 사용자가 공유하는 캐시가 아니다. 트랜잭션 내부에서만 공유되는 캐시이다.
- 변경 감지시 1차 캐시의 스냅샷데이터와 Entity 데이터를 비교한 다음 변경사항이 발견되면 Update 쿼리를 쓰기 지연 SQL 저장소에 저장 후 commit()함수가 실행될 때 실제 쿼리를 DBMS에 날린다.

```
Member member = new Member();
member.setId("m1");
member.setUsername("이름");

/* 
    1차 캐시에 저장 
    쓰기 지연 SQL 저장소에 insert SQL문을 저장
*/
entityManager.persist(member);

// 1차 캐시에서 조회
Member findMember1 = em.find(Member.class, "m1");
Member findMember2 = em.find(Member.class, "m1");

// 실제 쿼리문 전달.
entityTransaction.commit();

/* findMember1 == findMember2
 1차 캐시로 Repeatable Read 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공한다.
*/
```
|@id|Entity|스냅샷|
|---|------|------|
|m1|member(개체)|member개체 스냅샷|

## 4-c. 플러시
- 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
- 플리시를 해도 1차 캐시 데이터는 유지된다. 영속성 컨텍스트를 비우는게 아니라 변경된 내용을 데이터베이스에 동기화하는 것이다.
- 플러시가 발생하면 "변경감지"-"수정된 엔티티에 대한 쿼리문이 쓰기 지연 SQL 저장소에 등록"-"쓰기 지연 SQL 저장소에 있는 쿼리문을 DB로 전송"
- 영속성 컨텍스를 플러시하는 방법
    - entityManger.flush(); 직접 호출
    - entityTransaction.commit(); 플러시 자동 호출
    - JPQL 쿼리 실행; 플러시 자동 호출 
- 플러시 모드 옵션 entityManager.setFlushMode(모드);
    - FlushModeType.AUTO: 커밋이나 쿼리를 실행할 때 플러시
    - FlushModeType.COMMIT: 커밋할 때만 플러시

## 4-d. 준영속 상태
- 준영속 상태로 만드는 방법
    - 특정 엔티티만 준영속 상태로 전환: entityManager.detach(개체);
    - 영속성 컨텍스트를 완전 초기화: entityManger.clear();
    - 영속성 컨텍스트를 종료: entityManager.close();
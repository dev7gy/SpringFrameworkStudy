package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * resources/META-INF/persistence.xml 파일에서 <persistence-unit name="hello"> 유닛명이 hello기 때문에 아래 코드도 hello
         * EntityManagerFactory 웹 서버 올라갈 때 하나만 생성된다. 애플리케이션 전체에서 공유
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /**
         * EntityManager는 DB를 사용하는 요청이 있을 때마다 EntityManagerFactory를 통해 생성되어서 사용하는 것.
         * !중요! 그렇기 때문에 쓰레드 간 공유X -> 사용하고 버려야 한다.
         */
        EntityManager entityManager = emf.createEntityManager();

        /**
         * !중요! JPA의 모든 데이터 변경은 transaction 안에서 실행해야 한다.
         */
        EntityTransaction entityTransaction = entityManager.getTransaction();
        // begin()을 통해 데이터베이스 트랜잭션을 시작한다.
        entityTransaction.begin();
        try {
            Member findMember = entityManager.find(Member.class, 1L);
            // entityManager.persist(member); helloMember // 데이터 insert
            findMember.setName("HelloChanged");
            // entityManager.remove(findMember); // 데이터 delete
            System.out.println("findMember ID = " + findMember.getId());
            System.out.println("findMember Name= " + findMember.getName());
            // 정상적일 경우 commit
            entityTransaction.commit();
        } catch (Exception e) {
            // 문제가 있으면 rollback
            entityTransaction.rollback();
        } finally {
            // entityManager가 DB 커넥션을 잡고 있기 때문에 끊어줘야함.
            entityManager.close();
        }
        emf.close();
    }
}

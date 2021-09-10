package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        /*
        resources/META-INF/persistence.xml 파일에서 <persistence-unit name="hello"> 유닛명이 hello기 때문에 아래 코드도 hello
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = emf.createEntityManager();

        /*
        !중요! JPA에서는 transaction이 굉장히 중요하다.
         */
        EntityTransaction entityTransaction = entityManager.getTransaction();
        // begin()을 통해 데이터베이스 트랜잭션을 시작한다.
        entityTransaction.begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("helloMember");
            entityManager.persist(member);
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

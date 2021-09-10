package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * EntityManagerFactory 웹 서버 올라갈 때 하나만 생성된다. 애플리케이션 전체에서 공유
         * resources/META-INF/persistence.xml 파일에서 <persistence-unit name="hello"> 유닛명이 hello기 때문에 아래 코드도 hello
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
            // jpql 간단 예시 -> 엔티티 객체를 대상으로 쿼리 (객체 지향 쿼리)
            List<Member> memberList = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10) // from member member0_ limit ? offset ? 페이징 처리도 편해진다.
                    .getResultList();
            for (Member member: memberList) {
                System.out.println("member Name = " + member.getName());
            }
            // 정상적일 경우 commit
            entityTransaction.commit();
        } catch (Exception e) {
            // 문제가 있으면 rollback
            entityTransaction.rollback();
        } finally {
            // !중요! entityManager가 DB 커넥션을 잡고 있기 때문에 반드시 자원 반환을 해줘야한다.
            entityManager.close();
        }
        emf.close();
    }
}

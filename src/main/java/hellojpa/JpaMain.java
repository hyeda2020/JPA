package hellojpa;

import hellojpa.domain.Member;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 엔티티 매니저는 쓰레드간 공유 X (사용하고 바로 close)

        EntityTransaction tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 내에서 실행
        tx.begin();

        //code
        try {
            Member member = new Member();
            member.setName("MemberA");

            em.persist(member);

            Member memberA = em.find(Member.class, 1L);
            System.out.println("find : " + memberA.getName());

            memberA.setName("MemberB"); // Setter 로 업데이트 하면 바로 DB에 반영(변경 감지)

            Member memberB = em.find(Member.class, 1L);
            System.out.println("find : " + memberB.getName());

            // JPQL 활용 예시(JPQL 은 엔티티 객체를 대상으로 한 쿼리)
            // JPA는 SQL을 추상화한 JPQL 이라는 객체 지향 쿼리 언어 제공
            List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            for (Member m : memberList) {
                System.out.println("memberList : " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

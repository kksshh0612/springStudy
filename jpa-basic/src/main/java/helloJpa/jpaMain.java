package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();       //emf가 엔티티매니저 만듦

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();        //트랜잭션 시작. jpa의 모든 데이터 변경은 트랜잭션 안에서 실행된다

        try{
            //디비에 데이터를 저장하거나 이런 코드들이 이 사이에 들어가는 것.
            //데이터 삽입
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("memberB");
//            em.persist(member);

            //데이터 검색 (select)
//            Member member = em.find(Member.class, 1L);

            //데이터 삭제 (delete)
//            em.remove(member);

            //데이터 수정 (update)
//            member.setName("memberAAAAAAA");

            //JPQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)     //멤버 엔티티를 통하여 가져옴.
//                            .getResultList();
//            for(Member member : result){
//                System.out.println("멤버 이름 : " + member.getName());
//            }

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

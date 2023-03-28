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

            //쓰기지연 테스트
//            Member member1 = new Member(3L, "a");
//            Member member2 = new Member(4L, "b");
//
//            em.persist(member1);
//            em.persist(member2);            //이 순간까지 쓰기지연 SQL 저장소에 저장된다.
//
//            System.out.println("============================");

            //변경감지 테스트
//            Member member = em.find(Member.class, 3L);
//            member.setName("aaaaaaaaaaaaa");                //데이터 변경하면 DB 값 알아서 변경됨. 마치 자바 컬렉션을 다루듯이
//
//            System.out.println("============================");

            //플러시 테스트
//            Member member = new Member(5L, "c");
//            em.persist(member);         //여기까지 하면 쿼리가 저장소에 담겨있고

            //시퀀스 테스트
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("==================");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            em.flush();                 //플러시를 통해 쿼리를 디비에 먼저 반영할 수 있음. 플러시

            System.out.println("================");

            transaction.commit();
        } catch (Exception e){          //문제 생기면 롤백
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

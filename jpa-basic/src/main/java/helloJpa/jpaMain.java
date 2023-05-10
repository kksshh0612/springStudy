package helloJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.awt.*;
import java.security.spec.ECField;
import java.time.LocalDateTime;
import java.util.List;

public class jpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{

            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }


//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();       //emf가 엔티티매니저 만듦
//
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();        //트랜잭션 시작. jpa의 모든 데이터 변경은 트랜잭션 안에서 실행된다
//
//        try{
//            //디비에 데이터를 저장하거나 이런 코드들이 이 사이에 들어가는 것.
//            //데이터 삽입
////            Member member = new Member();
////            member.setId(2L);
////            member.setName("memberB");
////            em.persist(member);
//
//            //데이터 검색 (select)
////            Member member = em.find(Member.class, 1L);
//
//            //데이터 삭제 (delete)
////            em.remove(member);
//
//            //데이터 수정 (update)
////            member.setName("memberAAAAAAA");
//
//            //JPQL
////            List<Member> result = em.createQuery("select m from Member as m", Member.class)     //멤버 엔티티를 통하여 가져옴.
////                            .getResultList();
////            for(Member member : result){
////                System.out.println("멤버 이름 : " + member.getName());
////            }
//
//            //쓰기지연 테스트
////            Member member1 = new Member(3L, "a");
////            Member member2 = new Member(4L, "b");
////
////            em.persist(member1);
////            em.persist(member2);            //이 순간까지 쓰기지연 SQL 저장소에 저장된다.
////
////            System.out.println("============================");
//
//            //변경감지 테스트
////            Member member = em.find(Member.class, 3L);
////            member.setName("aaaaaaaaaaaaa");                //데이터 변경하면 DB 값 알아서 변경됨. 마치 자바 컬렉션을 다루듯이
////
////            System.out.println("============================");
//
//            //플러시 테스트
////            Member member = new Member(5L, "c");
////            em.persist(member);         //여기까지 하면 쿼리가 저장소에 담겨있고
//
//            //시퀀스 테스트
////            Member member1 = new Member();
////            member1.setUsername("A");
////
////            Member member2 = new Member();
////            member2.setUsername("B");
////
////            Member member3 = new Member();
////            member3.setUsername("C");
////
////            System.out.println("==================");
////
////            em.persist(member1);
////            em.persist(member2);
////            em.persist(member3);
////
////            System.out.println("member1 = " + member1.getId());
////            System.out.println("member2 = " + member2.getId());
////            System.out.println("member3 = " + member3.getId());
////
////            em.flush();                 //플러시를 통해 쿼리를 디비에 먼저 반영할 수 있음. 플러시
////
////            System.out.println("================");
//
//            //아래 코드를 보면 팀 객체를 만들고, 영속성 컨텍스트에 넣고,(insert문 만들어짐)
//            //멤버 객체를 만들고 팀 값을 설정한 후 영속성 컨텍스트에 넣고,(insert문 만들어짐)
//            //팀 객체에 있는 멤버 리스트에 멤버를 넣어주지 않고 플러시도 해주지 않으면, 문제가 발생한다.
//            //Member findMember = em.find(Member.class, member.getId()); 이 부분에서 트랜잭션 커밋이 일어나지 않은 상태이기 때문에
//            //멤버 객체는 영속성 컨텍스트에서 가져온다. 그리고 그 멤버 객체에서 팀 객체에 접근해 팀 객체 안에 있는 멤버 리스트를 가져오면
//            //당연히 값은 null이다. 왜냐면 쿼리가 디비로 날아가기 전이라 팀 테이블에 있는 멤버 리스트에 객체가 들어있지 않기 때문이다.
//            //그래서 객체지향의 관점에서 볼 때, 양쪽에 값을 설정해줘야 한다.
//
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team);
//            em.persist(member);
//
////            team.getMembers().add(member);          //팀 쪽에도 멤버 값을 넣어줌.
//
////            em.flush();
////            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());      //1차캐시에서 가져옴
//            List<Member> members = findMember.getTeam().getMembers();       //멤버 객체에서 팀 객체에 접근하고 팀 객체에 있는 멤버 리스트 리턴
//
//            System.out.println("==============");
//            for(Member m : members){
//                System.out.println("member = " + m.getUsername());
//            }
//            System.out.println("==============");
//
////            Team findTeam = findMember.getTeam();
//
//            transaction.commit();
//        } catch (Exception e){          //문제 생기면 롤백
//            transaction.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//    }
}

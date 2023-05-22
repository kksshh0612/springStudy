package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();     //트랜잭션 시작

        try {
//            for(int i = 0; i < 100; i++){
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);


            //파라미터 있는 경우
//            Member result = em.createQuery("select m from Member m where m.username = :uName", Member.class)
//                            .setParameter("uName", "member1")
//                                    .getSingleResult();
//
//            System.out.println(result.getId() + " " + result.getUsername());

            //여러 프로젝션 new를 사용해서 조회. 기존 Object 배열로 사용하지 않고, 객체에 담아서 사용
//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                            .getResultList();
//            MemberDTO memberDTO = result.get(0);        //첫번째 값
//            System.out.println(memberDTO.getUsername());

            //페이징
//            List<Member> result = em.createQuery("select m from Member m order by m.username desc", Member.class)
//                            .setFirstResult(0)
//                                    .setMaxResults(10)
//                                            .getResultList();       //0번째부터 10개
//
//            System.out.println(result.size());
//            for(Member memberVal : result){
//                System.out.println(memberVal.getUsername());
//            }

            //조인
//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            System.out.println(result.get(0).getUsername());

            //case
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' " +
//                            "else '일반 요금' " +
//                            "end " +
//                    "from Member m";
//
//            List<String> result = em.createQuery(query, String.class)
//                            .getResultList();
//
//            System.out.println(result.get(0));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
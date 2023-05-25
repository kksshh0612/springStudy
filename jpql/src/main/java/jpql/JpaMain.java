package jpql;

import jakarta.persistence.*;

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

            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);
            member1.changeTeam(team1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(11);
            member2.changeTeam(team2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setAge(12);
            member3.changeTeam(team2);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

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

            //JPQL 기본 함수
//            String query = "select substring(m.username, 2, 4) from Member m";
//
//            List<String> result = em.createQuery(query, String.class)
//                            .getResultList();
//
//            for(String s : result){
//                System.out.println("잘린 문자열 : " + s);
//            }

            //사용자 등록 함수는 필요하면 그때 공부해서 하기

            //경로표현식
//            String query = "select t.members from Team t";
//
//            List<Member> result = em.createQuery(query, Member.class)
//                            .getResultList();
//
//            for(Member memberName : result){
//                System.out.println(memberName);
//            }

            //페치 조인 (문제) -> N + 1문제 발생
//            String query = "select m from Member m";
//
//            List<Member> members = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            for(Member member : members){
//                System.out.println(member.getUsername() + ", " + member.getTeam().getName());
//            }
            //위 코드를 실행하면 팀이 2개 있기 때문에 쿼리가 멤버를 조회하는 쿼리 1개, 팀을 조회하는 쿼리 2개가 나가게 된다.
            //만약 멤버가 1000명에 팀이 1000개가 있다면? 쿼리가 1000개 나가게 된다. ----> N + 1 문제 발생

            //페치 조인 (해결)
//            String query = "select m from Member m join fetch m.team";
//
//            List<Member> members = em.createQuery(query, Member.class)
//                            .getResultList();
//
//            for(Member member : members){
//                System.out.println(member.getUsername() + ", " + member.getTeam().getName());
//            }

            //컬렉션 페치 조인
//            String query = "select t from Team t join fetch t.members where t.name = 'teamB'";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                            .getResultList();
//
//            for(Team team : result){
//                System.out.println("team = " + team.getName() + " members = " + team.getMembers());
//                for(Member member : team.getMembers()){
//                    System.out.println(team + " ->  " + member);
//                }
//            }

            //Named 쿼리
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                            .setParameter("username", "member1")
//                                    .getResultList();
//
//            for(Member member : resultList){
//                System.out.println(member.getUsername());
//            }

            //벌크 연산
            int resultCnt = em.createQuery("update Member m set m.age = 20")
                            .executeUpdate();

            em.clear();         //영속성 컨텍스트 초기화

            Member member = em.find(Member.class, member1.getId());

            System.out.println(member.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
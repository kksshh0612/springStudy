package springbootjpaprac.springbootjpaprac.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springbootjpaprac.springbootjpaprac.domain.Member;

import java.util.List;

@Repository     //자동으로 스프링 빈으로 관리됨.
public class MemberRepository {

    @PersistenceContext     //엔티티 매니저 생성해서 주입
    private EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }

    public Member find(Long id){
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll(){
        String query = "select m from Member m";
        List<Member> resultList = entityManager.createQuery(query, Member.class)
                .getResultList();

        return resultList;
    }

    public List<Member> findByName(String name){
        String query = "select m from Member m where m.name = :name";
        List<Member> resultList = entityManager.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();

        return resultList;
    }
}

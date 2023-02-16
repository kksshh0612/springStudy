package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     //스프링에서 컴포넌트 스캔을 통해 자동으로 빈으로 등록
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext       //엔티티는 얘를 이용해서 의존관계 주입이 된다. 그런데 스프링 데이터 JPA에서는 @Autowired를 이용할 수 있도록 해준다.
    private final EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }

    public Member findOne(Long id){
        return entityManager.find(Member.class, id);        //Member 객체
    }

    public List<Member> findAll(){
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}

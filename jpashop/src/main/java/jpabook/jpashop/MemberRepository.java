package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //회원 정보 저장
    public Long save(Member member){        //멤버를 반환하지 않고 커맨드와 쿼리를 분리해라.
        entityManager.persist(member);
        return member.getId();
    }

    //일치하는 회원 조회
    public Member find(Long id) {
        return entityManager.find(Member.class, id);
    }
}

package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");

        Member member1 = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(member1.getId());

        Assertions.assertThat(member1.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(member1.getUsername()).isEqualTo(findMember.getUsername());
        Assertions.assertThat(member1).isEqualTo(findMember);       //같은 인스턴스 -> 1차 캐시
    }
}

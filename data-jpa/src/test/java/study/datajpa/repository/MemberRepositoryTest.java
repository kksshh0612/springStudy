package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member member1 = memberRepository.save(member);

        Member findMember = memberRepository.findById(member1.getId()).get();

        Assertions.assertThat(member1.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(member1.getUsername()).isEqualTo(findMember.getUsername());
        Assertions.assertThat(member1).isEqualTo(findMember);       //같은 인스턴스 -> 1차 캐시
    }
}

package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional                  // 테스트가 끝난 후 DB를 롤백함. (이전으로 되돌림)
    @Rollback(value = false)
    public void testMember() throws Exception {
        //Given
        Member member = new Member();
        member.setUsername("memberA");

        //When
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //Then
        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
        assertThat(findMember).isEqualTo(member);           //같은 이유?
    }
}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest      //@RunWith(SpringRunner.class) 포함
@Transactional      //기본적으로 롤백해서 이전 상태로 DB를 되돌림. 따라서 insert문을 날릴 이유가 없음
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        assertThat(member).isEqualTo(memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외처리() throws Exception{
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        Long savedId1 = memberService.join(member1);

        IllegalStateException thrown = assertThrows(IllegalStateException.class,
                ()->memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }
}

package springbootjpaprac.springbootjpaprac.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springbootjpaprac.springbootjpaprac.domain.Member;
import springbootjpaprac.springbootjpaprac.repository.MemberRepository;

@SpringBootTest
@Transactional      //기본적으로 롤백 함. -> insert 쿼리 날리지 않음
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");

        //when
        Long savedId = memberService.join(member);

        //then

        Assertions.assertEquals(member, memberRepository.find(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("memberB");
        Member member2 = new Member();
        member2.setName("memberB");

        //when
        memberService.join(member1);

        //then
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () ->
            memberService.join(member2));
        Assertions.assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }
}

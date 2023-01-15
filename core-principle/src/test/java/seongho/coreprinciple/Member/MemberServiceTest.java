package seongho.coreprinciple.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given : 이런게 주어지고
        Member member = new Member(1L, "호성김", Grade.VIP);

        //when : 이런 동작 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이런 결과 나온다
        Assertions.assertThat(member).isEqualTo(findMember);        //저장한 멤버와 찾은 멤버가 같은지 확인

    }
}

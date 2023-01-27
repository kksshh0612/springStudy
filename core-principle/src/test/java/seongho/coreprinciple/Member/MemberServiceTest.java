package seongho.coreprinciple.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){                       //왜 테스트에서는 이렇게 하는지 생각. 독립성 있느 테스트를 위해서. 이전에 저장된 객체의 영향을 받지 않게 하기 위함.
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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

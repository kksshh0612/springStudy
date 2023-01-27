package seongho.coreprinciple.Order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.order.Order;
import seongho.coreprinciple.order.OrderService;
import seongho.coreprinciple.order.OrderServiceImpl;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){                       //왜 테스트에서는 이렇게 하는지 생각
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();      //멤버 레포지토리 지정.
        orderService = appConfig.orderService();        //멤버 레포지토리와 할인 정책 지정
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "ㄱ밋어호", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "A", 50000);       //order 만들어서 Order의 객체 반환
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(5000);        //정률 할인정책 적용중
    }
}

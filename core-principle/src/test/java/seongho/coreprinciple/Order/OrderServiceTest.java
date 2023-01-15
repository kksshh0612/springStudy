package seongho.coreprinciple.Order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.order.Order;
import seongho.coreprinciple.order.OrderService;
import seongho.coreprinciple.order.OrderServiceImpl;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "ㄱ밋어호", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "A", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

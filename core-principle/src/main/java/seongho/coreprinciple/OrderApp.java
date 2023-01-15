package seongho.coreprinciple;

import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.order.OrderService;
import seongho.coreprinciple.order.OrderServiceImpl;
import seongho.coreprinciple.order.Order;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "김성호", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "A", 10000);

        System.out.println("주문 = " + order);
    }
}

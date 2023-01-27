package seongho.coreprinciple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.order.OrderService;
import seongho.coreprinciple.order.OrderServiceImpl;
import seongho.coreprinciple.order.Order;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);          //스프링 컨테이너
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);     //스프링 빈
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);         //스프링 빈


        Long memberId = 1L;
        Member member = new Member(memberId, "김성호", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "A", 30000);

        System.out.println("주문 = " + order);
    }
}

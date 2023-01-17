package seongho.coreprinciple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seongho.coreprinciple.discount.DiscountPolicy;
import seongho.coreprinciple.discount.FixDiscountPolicy;
import seongho.coreprinciple.discount.RateDiscountPolicy;
import seongho.coreprinciple.member.MemberRepository;
import seongho.coreprinciple.member.MemberService;
import seongho.coreprinciple.member.MemberServiceImpl;
import seongho.coreprinciple.member.MemoryMemberRepository;
import seongho.coreprinciple.order.OrderService;
import seongho.coreprinciple.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());     //생성자 주입
    }

    @Bean
    public MemberRepository memberRepository(){     //중복을 제거하고 역할이 보이도록 구현
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){         //중복을 제거하고 역할이 보이도록 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

//import seongho.coreprinciple.discount.DiscountPolicy;
//import seongho.coreprinciple.discount.FixDiscountPolicy;
//import seongho.coreprinciple.discount.RateDiscountPolicy;
//import seongho.coreprinciple.member.MemberRepository;
//import seongho.coreprinciple.member.MemberService;
//import seongho.coreprinciple.member.MemberServiceImpl;
//import seongho.coreprinciple.member.MemoryMemberRepository;
//import seongho.coreprinciple.order.OrderService;
//import seongho.coreprinciple.order.OrderServiceImpl;

//public class AppConfig {            //구성 영역으로, 모든 객체를 다 알고있어야 한다.
//
////    리팩토링 전 (new MemoryMemberRepository()의 중복이 있고, 역할을 정확히 알 수 없다. 그리고 역할과 구현을 철저히 분리한다.)
////    public MemberService memberService() {
////        return new MemberServiceImpl(new MemoryMemberRepository());
////    }
////    public OrderService orderService() {
////        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
////    }
//
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());     //생성자 주입
//    }
//
//    public MemberRepository memberRepository(){     //중복을 제거하고 역할이 보이도록 구현
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public DiscountPolicy discountPolicy(){         //중복을 제거하고 역할이 보이도록 구현
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//
//}

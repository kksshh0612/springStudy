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
public class AppConfig {        //이 컨트롤러에서 함수를 호출하면 생성자 주입을 실행한다. 모든 구현 객체를 주입하는 것은 여기서 처리한다.
    //공연 기획자임. 역할과 구현을 모두 알아야함.


    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());     //생성자 주입
    }

    @Bean
    public MemberRepository memberRepository(){     //중복을 제거하고 역할이 보이도록 구현
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();                        //여기서 구현 클래스를 선택.
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){         //중복을 제거하고 역할이 보이도록 구현. 이제, 할인정책을 변경하고 싶으면 이 부분만 변경하면됨.
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
//          return new MemberServiceImpl(new MemoryMemberRepository());
//      }
//      public OrderService orderService() {
//          return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());     //MemoryMemberRepository()와 FixDiscountPolicy()를 분리시키기
//      }
//
//}

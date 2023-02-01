package seongho.coreprinciple.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import seongho.coreprinciple.annotation.MainDiscountPolicy;
import seongho.coreprinciple.discount.RateDiscountPolicy;
import seongho.coreprinciple.order.Order;
import seongho.coreprinciple.discount.DiscountPolicy;
import seongho.coreprinciple.discount.FixDiscountPolicy;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberRepository;
import seongho.coreprinciple.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService{      //OrderServiceImpl 구현 클래스 입장에서는 어떤 구현 객체가 들어오는지 모른다.

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();    //DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();     //DIP 위반. 그리고 정책을 변경하는 순간 OCP를 위반한다.
    private final DiscountPolicy discountPolicy;      //이렇게 구현하고 인터페이스에만 의존한다.

//    @Autowired      //동일한 빈 타입이 두 개 이상일 때. 파라미터 이름을 빈 이름으로 지정
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {     //생성자 주입. 어떤 구현 객체가 들어오는지 모른다.
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

//    @Autowired            //@Qualifier 사용
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {     //생성자 주입. 어떤 구현 객체가 들어오는지 모른다.
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired            //애너테이션 만들기
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {     //생성자 주입. 어떤 구현 객체가 들어오는지 모른다.
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {     //생성자 주입. 어떤 구현 객체가 들어오는지 모른다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);     //주문 생성
    }

    //싱글톤 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

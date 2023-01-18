package seongho.coreprinciple.order;

import seongho.coreprinciple.discount.RateDiscountPolicy;
import seongho.coreprinciple.order.Order;
import seongho.coreprinciple.discount.DiscountPolicy;
import seongho.coreprinciple.discount.FixDiscountPolicy;
import seongho.coreprinciple.member.Member;
import seongho.coreprinciple.member.MemberRepository;
import seongho.coreprinciple.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();    //DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();     //DIP 위반. 그리고 정책을 변경하는 순간 OCP를 위반한다.
    private final DiscountPolicy discountPolicy;      //이렇게 구현하고 인터페이스에만 의존한다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

package seongho.coreprinciple.discount;

import seongho.coreprinciple.member.Member;

// 할인 정책을 결정하는 인터페이스
public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */

    int discount(Member member, int price);
}

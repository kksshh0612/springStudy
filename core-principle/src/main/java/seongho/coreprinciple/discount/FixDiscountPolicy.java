package seongho.coreprinciple.discount;

import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;       //할인 금액

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else {
            return 0;
        }
    }
}

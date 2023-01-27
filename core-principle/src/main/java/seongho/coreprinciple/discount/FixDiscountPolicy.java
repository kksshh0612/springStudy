package seongho.coreprinciple.discount;

import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{       //DiscountPolicy의 구현 클래스. 고정 할인 정책

    private int discountFixAmount = 1000;       //할인 금액

    @Override
    public int discount(Member member, int price) {             //멤버 객체와 주문 금액을 받아 할인 금액을 리턴하는 함수.
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else {              //VIP가 아니면 할인금액 0
            return 0;
        }
    }
}

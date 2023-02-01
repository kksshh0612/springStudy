package seongho.coreprinciple.discount;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import seongho.coreprinciple.annotation.MainDiscountPolicy;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;

@Component
@Primary
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy         //내가 만든 애너테이션
public class RateDiscountPolicy implements DiscountPolicy{      //DiscountPolicy의 구현클래스. 정률 할인 정책
    private int discountPercent = 10;       //할인률

    @Override
    public int discount(Member member, int price) {             //멤버 객체와 주문 금액을 매개변수로 받아 할인 금액을 계산 후 리턴
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        }
        else{               //VIP가 아니면 할인 금액 0
            return 0;
        }
    }
}

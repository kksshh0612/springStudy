package seongho.coreprinciple.Discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seongho.coreprinciple.AppConfig;
import seongho.coreprinciple.discount.RateDiscountPolicy;
import seongho.coreprinciple.member.Grade;
import seongho.coreprinciple.member.Member;

public class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();      //정률 할인 정책의 로직을 테스트하기 위함.

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVip", Grade.VIP);     //VIP등급의 멤버 객체 만들고

        //when
        int discount = discountPolicy.discount(member, 20000);

        //then
        Assertions.assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        //given
        Member member = new Member(1L, "memberBasic", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(discount).isEqualTo(0);       //할인이 적용되지 않아, 할인 금액은 0이어야 한다.
    }
}

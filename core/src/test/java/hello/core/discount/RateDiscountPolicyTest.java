package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용 돼야 한다.")
    void vip_o(){
        //given
        Member memberA = new Member(1l, "memberA", Grade.VIP);
        //when
        int discount = discountPolicy.discount(memberA, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 10프로 할인이 적용 되면 안된다.")
    void vip_x(){
        //given
        Member member = new Member(1l,"Member",Grade.BASIC);
        //when

        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}
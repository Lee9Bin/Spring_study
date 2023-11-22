package hi.core.discount;

import hi.core.member.Grade;
import hi.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPersent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPersent / 100;
        } else {
            return 0;
        }
    }
}

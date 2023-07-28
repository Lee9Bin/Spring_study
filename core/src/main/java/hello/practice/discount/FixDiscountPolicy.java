package hello.practice.discount;

import hello.practice.member.Grade;
import hello.practice.member.Member;
import hello.practice.member.MemberMemoryRepositoryImpl;
import hello.practice.member.MemberRepository;

public class FixDiscountPolicy implements DiscountPolicy{
    static int discountAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
        return discountAmount;
        }
        else {
            return 0;
        }
    }
}

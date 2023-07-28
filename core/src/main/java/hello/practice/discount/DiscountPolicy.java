package hello.practice.discount;

import hello.practice.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}

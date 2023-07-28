package hello.practice.order;

import hello.practice.discount.DiscountPolicy;
import hello.practice.discount.FixDiscountPolicy;
import hello.practice.member.Member;
import hello.practice.member.MemberMemoryRepositoryImpl;
import hello.practice.member.MemberRepository;

public class OrderServiceImple implements OrderService{
    private MemberRepository memberRepository = new MemberMemoryRepositoryImpl();
    private DiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int price) {
        Member member = memberRepository.findBy(memberId);
        int discount = fixDiscountPolicy.discount(member,price);
        return new Order(memberId,itemName,price,discount);
    }
}

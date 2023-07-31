package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 인터페이스를 활용하여 역할과 구현을 분리했다. 다형성도 활용했다.  -> 하지만 DiscountPolicy 추상클래스 뿐만아니라
    // 구현인 FixDiscountPolicy, RateDiscountPolicy 에도 의존.. DIP를 지킨줄 알았지만 지켜지지 않고 있어
    // 또한 FixDiscountPolicy -> RateDiscountPolicy 변경하면서 OCP를 어기고 있어
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 알고봤더니 어떤 구현객체를 쓸지 결정하는 책임도 하게 되네 후
    private final DiscountPolicy discountPolicy; // -> 이렇게 바꾸면 인터페이스만 의존하는 상태가 돼 DIP 지켜진다.

    // 생성자 주입을 통해 인터페이스에만 의존하게 만들어!!!!!!!!
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findBy(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}

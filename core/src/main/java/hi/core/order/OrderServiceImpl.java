package hi.core.order;

import hi.core.discount.DiscountPolicy;
import hi.core.member.Member;
import hi.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    //새로운 할인정책을 적용하려고 했지만 클라이언트의 코드가 바뀐다. 왜냐 서비스임플이 어떤 구현체를 쓸건지 선택을 하는 상황이야
    // 마치 공연에서 배우가 어떤 상대 배우랑 할건지 정하는거지
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 인터페이스에만 의존하도록 해서 DIP를 지키게 했지만 구현객체가 없어서 돌아가질 않아 ...null인 상황이야

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
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

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

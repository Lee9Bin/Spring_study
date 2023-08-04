package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@Component
// @RequiredArgsConstructor
// public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//     this.memberRepository = memberRepository;
//     this.discountPolicy = discountPolicy;
// } -> 생성자를 만들어주는 역할
public class OrderServiceImpl implements OrderService{

    // @Autowired //필드주입 - 코드도 간결하고 좋지만 외부에서 변경이 불가능 해서 테스트하기 힘들다.
    private final MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 인터페이스를 활용하여 역할과 구현을 분리했다. 다형성도 활용했다.  -> 하지만 DiscountPolicy 추상클래스 뿐만아니라
    // 구현인 FixDiscountPolicy, RateDiscountPolicy 에도 의존.. DIP를 지킨줄 알았지만 지켜지지 않고 있어
    // 또한 FixDiscountPolicy -> RateDiscountPolicy 변경하면서 OCP를 어기고 있어
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 알고봤더니 어떤 구현객체를 쓸지 결정하는 책임도 하게 되네 후
    // @Autowired //필드주입
    private final DiscountPolicy discountPolicy; // -> 이렇게 바꾸면 인터페이스만 의존하는 상태가 돼 DIP 지켜진다.

    // 생성자 주입-생성자 주입을 통해 인터페이스에만 의존하게 만들어!!!!!!!!
    // 불편 필수 관계에서
    // final 키워드를 쓸수 있다.
    // 생성자가 딱 1개만 있으면 @Autowired 생략해도 자동 주입 된다. -why? 스프링 빈을 등록하면서 클래스를 호출하려면 생성자를 호출해야해
    @Autowired
    // Autowired 타입이 여록개라면 파라미터에 있는 이름이 같은 놈을 가져온다.
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    //
    // // 일반 메서드 주입
    // @Autowired
    // public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    //
    // }

    // 수정자 주입 - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법.
    // 선택, 변경 가능성이 있는 관계에서
    // @Autowired
    // public void setMemberRepository(MemberRepository memberRepository) {
    //     this.memberRepository = memberRepository;
    // }
    // @Autowired
    // public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findBy(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*기획자 같은 역할이라고 생각하면 돼
* 역할에 따른 배우들이 다른 배우를 선택해야해 ? -> 아니지 그러면 안되지 배우는 역할에만 충실하면 되는데 왜 다른 역할에 누구를 쓸지를 생각해야하는데?
* 그래서 기획자 개념이 나타는거야 AppConfig와 같이 누구를 쓸지는 이친구가 결정하는거야
* 그러면서 SRP, DIP, OCP를 지킬수 있게 되는거지!*/

@Configuration //스프링에서 설정정보라는 것이라고 알려주는 애너테이션
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository();
    //@Bean orderService -> new MemoryMemberRepository(); , new discountPolicy();
    // @Bean memberRepository -> new MemoryMemberRepository();
    // 어어어어어? 당장 MemoryMemberRepository()가 세번 실행 되면서 싱글톤이 깨지는거아냐?
    //->실험 고고씽 같은 인스턴스를 가르키고 있어

    // 그럼 호출로그는 ?
    // 예상) (순서 보장은 안되지만) call AppConfig.memberService -> call AppConfig.memberRepository ->call AppConfig.memberRepository
    //      ->call AppConfig.orderService -> call AppConfig.memberRepository  결과적으로는 memberRepository3번 호출되는지 확인
    // 어이쿠 call AppConfig.memberService -> call AppConfig.memberRepository ->call AppConfig.orderService 이렇게 되네?
    // 자바 코드상 우리가 예상한 것처럼 호출돼야하는게 맞아 하지만 스프링이 @Configuration이 붙은 구성정보 AppConfig를 사용하긴 하는데
    // 상속시킨 임의의 클래스를 사용해서 싱글톤을 보장해주는 것이였어

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        // return new OrderServiceImpl(memberRepository(),discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 구성영역에 코드만 바꾸면  -> 클라이언트 코드를 변경 안해두 돼!!!!!!!!! 즉 사용영역은 건들지 않게 되는거후
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderAppTest {
    MemberService memberService ;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService= appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // @Test
    // void fieldInjectionTest(){
    //     OrderServiceImpl orderService1 = new OrderServiceImpl();
    //     // 먼가 더미데이터를 가지고 테스트를 해보고 싶은데 필드 주입을 하면 넣어줄 방법이 없다.
    //     // ->그래서 set메서드를 만들어서 해야한다...
    //     orderService1.createOrder(1l,"item",10000);
    // }
}

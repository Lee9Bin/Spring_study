package hi.core.order;

import hi.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    @Test
    void 주문작성(){
        Long memberId = 1l;
        Member member = new Member(memberId, "memberA",Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);

    }
}

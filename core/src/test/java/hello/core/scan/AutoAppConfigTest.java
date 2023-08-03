package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = annotationConfigApplicationContext.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService = annotationConfigApplicationContext.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = orderService.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}

package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository MemberRepository1 = memberService.getMemberRepository();
        MemberRepository MemberRepository2 = orderService.getMemberRepository();

        // 똑같네??
        System.out.println("memberService -> MemberRepository = " + MemberRepository1);
        System.out.println("orderService -> MemberRepository = " + MemberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ad42d7f4
        // 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        // AppConfig 클래스 를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!
        System.out.println("bean = " + bean.getClass());

        // @Configuration 지우면 bean = class hello.core.AppConfig 원래의 우리 클래스가 나온다 !
        // 하지만 memberRepository가 3번 호출... 싱글톤이 깨지네 ㅎㅎㅎ 순수한 자바 코드가 돌아가는거지

        //즉 @Configuration을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장해!
    }
}

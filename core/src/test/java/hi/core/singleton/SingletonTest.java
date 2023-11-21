package hi.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    //= 스프링 없이 직접 AppConfig를 호출
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할때마다 객체를 생성 -> 호출 한번
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할때마다 객체를 생성 -> 호출 두번
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다르다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        // 결론 요청을 할때마다 새로운 객체를 생성된다. -> 요청을 들어올 때마다 객체를 만들면 비효율적
        // 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // new SingletonService(); private

        // 이미 static으로 선언된 객체를 가져다 쓰는거야.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();


        // 같은 객체 인스턴스가 반환!!!!!!!!
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //isSmaeAs: 참조 변수가 같은지 "==" 과 같다.
        //Equals: 객체의 내용이 같은지
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // AppConfig appConfig = new AppConfig();
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        // 참조값이 같다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);

        //어머나 스프링이 appconfig를 읽어서 빈을 만들어주면 싱글톤 객체로 관리가 된다!!!!!
        //기존의 싱글톤 패턴 단점은 다 보완하면서 장점만을 가져온다... 키야 단, 스프링의 기본 빈 등록 방식은 싱글톤이지만, 이 방식만 지원하는 것은 아님
        //요청할 때 마다 새로운 객체를 생성해서 반환하는 방식도 제공!
    }

}

package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan //Component애노테이션이 붙은 클래스를 스캔해 스프링 빈으로 등록해준다.
(
        basePackages = "hello.core.member", // 모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록 시작 위치를 지정할 수 있다.
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지로 지정, 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // @Bean(name = "memoryMemberRepository")
    // MemberRepository memberRepository(){
    //     return new MemoryMemberRepository();
    // }

}

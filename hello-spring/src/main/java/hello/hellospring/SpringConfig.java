package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링 빈 등록
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memoryRepository());
    }

    @Bean
    public MemberRepository memoryRepository(){
        return new MemoryMemberRepository();
    }
}

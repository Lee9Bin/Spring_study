package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링 빈 등록
@Configuration
public class SpringConfig {

    // private DataSource dataSource;
    //
    // @Autowired
    // public SpringConfig(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }
    //
    // private EntityManager em;
    //
    // @Autowired
    // public SpringConfig(EntityManager em) {
    //     this.em = em;
    // }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // @Bean
    // public TimeTraceAop TimeTraceAop(){
    //     return new TimeTraceAop();
    // }

    // @Bean
    // public MemberRepository memoryRepository(){
        // return new MemoryMemberRepository();
        // 다른 어떤 코드도 건들지 않고 클래스만 만들어 인터페이스를 확장시켜 congfig만 건들였어
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    // }
}

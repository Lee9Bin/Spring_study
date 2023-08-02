package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 이렇게만 하면 의존관계 주입은????
public class MemberServiceImpl implements MemberService{
    // 그래서 인터페이스로 선언하고 구현 클래스를 만드는 것
    // 다형성을 활용해서 코드를 구현했지만 문제가 있어 .... 다른 저장소로 바꿀때 OCP원칙이 깨져
    // 또한 DIP 원칙이 지켜지지 않아... (이래서 스프링 프레임워크가 필요?...) -> 사용영역과 구성영역을 나눠!
    private final MemberRepository memberRepository;

    // 생성자 주입을 통해 인터페이스에만 의존하게 만들어!!!!!!!!
    @Autowired //Component를 사용하면 수동으로 의존관계를 주입해주지 못해서 Autowired를 사용해서 주입!
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findBy(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

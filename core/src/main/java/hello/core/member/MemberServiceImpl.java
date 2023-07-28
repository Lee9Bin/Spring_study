package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // 그래서 인터페이스로 선언하고 구현 클래스를 만드는 것
    // 다형성을 활용해서 코드를 구현했지만 문제가 있어 .... 다른 저장소로 바꿀때 OCP원칙이 깨져
    // 또한 DIP 원칙이 지켜지지 않아... 이래서 스프링 프레임워크가 필요?...
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findBy(memberId);
    }
}

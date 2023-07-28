package hello.core.member;

// 인터페이스로 구현 -> 왜? 역할과 구현을 나누기 위해 -> 다형성과 OCP를 지키기 위함
public interface MemberService {
    // 회원가입 기능
    void join(Member member);
    // 조회기능
    Member findMember(Long memberId);
}

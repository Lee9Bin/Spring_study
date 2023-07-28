package hello.practice.member;

public interface MemberRepository {
    void save(Member member);

    Member findBy(Long memberId);
}

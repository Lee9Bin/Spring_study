package hello.practice.member;

import hello.practice.member.Member;
import hello.practice.member.MemberRepository;
import hello.practice.member.MemberService;
import hello.practice.member.MemberMemoryRepositoryImpl;

public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository = new MemberMemoryRepositoryImpl();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findBy(memberId);
    }
}

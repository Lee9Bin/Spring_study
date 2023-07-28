package hello.practice;


import hello.practice.member.Grade;
import hello.practice.member.Member;
import hello.practice.member.MemberService;
import hello.practice.member.MemberServiceImpl;

public class MemberTest {
    public static void main(String[] args) {
        Member memberA = new Member(1L,"memberA", Grade.VIP);
        MemberService memberService = new MemberServiceImpl();
        memberService.join(memberA);
        Member findMember = memberService.findMember(memberA.getMemberId());

        System.out.println("memberA = " + memberA.getMemberName());
        System.out.println("findMember = " + findMember.getMemberName());
    }
}

package hi.core;

import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);


        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("find = " + findMember.getName());
    }
}

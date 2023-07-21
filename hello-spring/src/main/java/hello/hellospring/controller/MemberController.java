package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    /*생성자를 통해 주입-> 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 권장*/
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    /*필드 주입-> 중간에 바꿀수 있는 방법이 없다.*/
    // @Autowired private MemberService memberService;

    /*setter 주입-> public하게 노출이 되는 위험*/
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }
}

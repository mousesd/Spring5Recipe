package net.homenet.controller;

import net.homenet.domain.Member;
import net.homenet.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/member/add")
    public String addMember(Model model) {
        Member member = new Member("Lee Sangdae", "000-1111-2222", "3333@gmail.com");
        memberService.add(member);
        List<Member> members = memberService.getMembers();

        model.addAttribute("members", members);
        return "memberList";
    }

    @RequestMapping(value = {"member/remove", "member/delete"}, method = RequestMethod.GET)
    public String removeMember(@RequestParam("memberName") String memberName) {
        memberService.remove(memberName);
        return "redirect:";
    }
}

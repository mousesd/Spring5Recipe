package net.homenet.controller;

import net.homenet.domain.Member;
import net.homenet.domain.Members;
import net.homenet.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/members")
    @ResponseBody
    public Members getMembers() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }

    @RequestMapping("/member/{memberId}")
    @ResponseBody
    public Member getMember(@PathVariable("memberId") long memberId) {
        return memberService.find(memberId);
    }
}

package net.homenet.controller;

import net.homenet.domain.Member;
import net.homenet.domain.Members;
import net.homenet.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ResponseEntity<Member> getMember(@PathVariable("memberId") long memberId) {
        Member member = memberService.find(memberId);
        if (member != null)
            return new ResponseEntity<>(member, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@RequestMapping(value = "/members", produces = MediaType.APPLICATION_XML_VALUE)
    //public String getMembersXml(Model model) {
    //    Members members = new Members();
    //    members.addMembers(memberService.findAll());
    //    model.addAttribute("members", members);
    //    return "xmlMemberTemplate";
    //}
    //
    //@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    //public String getMembers(Model model) {
    //    Members members = new Members();
    //    members.addMembers(memberService.findAll());
    //    model.addAttribute("members", members);
    //    return "jsonMemberTemplate";
    //}
}

package net.homenet.service;

import net.homenet.domain.Member;

import java.util.List;

public interface MemberService {
    void add(Member member);
    void remove(String memberName);
    List<Member> getMembers();
}

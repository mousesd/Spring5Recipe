package net.homenet.service;

import net.homenet.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final HashMap<String, Member> members = new HashMap<>();

    @Override
    public void add(Member member) {
        members.put(member.getName(), member);
    }

    @Override
    public void remove(String memberName) {
        members.remove(memberName);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members.values());
    }
}

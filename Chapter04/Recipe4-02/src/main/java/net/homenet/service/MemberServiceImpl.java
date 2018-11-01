package net.homenet.service;

import net.homenet.domain.Member;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    private final Map<Long, Member> members = new HashMap<>();

    @PostConstruct
    public void initialize() {
        Member member1 = new Member();
        member1.setName("Marten Deinum");
        member1.setEmail("marten@deinum.biz");
        member1.setPhone("000-0000-0000");

        Member member2 = new Member();
        member2.setName("John Doe");
        member2.setEmail("john@doe.com");
        member2.setPhone("111-1111-1111");

        Member member3 = new Member();
        member3.setName("John Doe");
        member3.setEmail("john@doe.com");
        member3.setPhone("222-2222-2222");

        members.put(1L, member1);
        members.put(2L, member2);
        members.put(3L, member3);
    }

    @Override
    public Collection<Member> findAll() {
        return members.values();
    }

    @Override
    public Member find(long id) {
        return members.get(id);
    }
}

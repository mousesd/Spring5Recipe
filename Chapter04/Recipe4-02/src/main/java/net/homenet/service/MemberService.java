package net.homenet.service;

import net.homenet.domain.Member;

import java.util.Collection;

public interface MemberService {
    Collection<Member> findAll();
    Member find(long id);
}

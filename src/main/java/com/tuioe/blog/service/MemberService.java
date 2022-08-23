package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepositroy memberRepositroy;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void Join(Member member){
        member.setRole("ROLE_USER");
        String password = member.getPassword();
        String EncPassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(EncPassword);
        memberRepositroy.save(member);
    }
}

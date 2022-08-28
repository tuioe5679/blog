package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepositroy memberRepositroy;

    // 패스워크 암호화
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 처리
    public void Join(MemberDTO dto){
        // 패스워드는 bCryptPasswordEncoder를 통해서 암호화 해야 Security 로그인 가능
        String EncPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        dto.setPassword(EncPassword);
        Member member = MemberDTO.memberCreate(dto);
        memberRepositroy.save(member);
    }
}

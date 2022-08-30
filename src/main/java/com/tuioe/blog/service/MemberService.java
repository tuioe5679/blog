package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService implements UserDetailsService {

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

    public List<MemberDTO> findAllMember(){
        List<Member> members = memberRepositroy.findAll();
        List<MemberDTO> responseDTOS = new ArrayList<>();
        for(Member member: members){ // 향상된 for문 사용 주로 배열에 사용
            responseDTOS.add(MemberDTO.create(member));
            //responseDTOS의 List 변수에 MemberDTO 생성하여 저장
        }
        return responseDTOS;
    }

    public MemberDTO findMember(int id){
        Member member = memberRepositroy.findById(id).get();
        return MemberDTO.create(member);
    }

    public void deleteMember(int id){
        Member member = memberRepositroy.findById(id).get();
        memberRepositroy.delete(member);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // email을 가지는 유저 정보를 찾아서 존재하면 리턴
        // 존재하지 않으면 null을 리턴
        Member member = memberRepositroy.findByEmail(email);
        if(member != null){
            return User.builder()
                    .username(member.getEmail())
                    .password(member.getPassword())
                    .roles(member.getRole())
                    .build();
        }
        return (UserDetails) new UsernameNotFoundException(email);
    }
}

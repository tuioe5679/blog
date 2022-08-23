package com.tuioe.blog.config.auth;

import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// login 요청이 오면 loadUserByUsername 함수를 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepositroy memberRepositroy;

    // 인자 이름과 login 페이지의 form에 있는 id와 이름이 같아야한다 (String email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // email을 가지는 유저 정보를 찾아서 존재하면 리턴
        // 존재하지 않으면 null을 리턴
        Member member = memberRepositroy.findByEmail(email);
        if(member != null){
            return new PrincipalDetails(member);
        }
        return null;
    }
}

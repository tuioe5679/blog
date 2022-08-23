package com.tuioe.blog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// login 요청이 오면 loadUserByUsername 함수를 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    // 인자 이름과 login 페이지의 form에 있는 id와 이름이 같아야한다
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }
}

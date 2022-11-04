package com.tuioe.blog.config;

import com.tuioe.blog.oauth.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    @Bean// 해당 메소드의 리턴되는 오브젝트를 IOC로 등록
    public BCryptPasswordEncoder encoder(){ // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    // extends WebSecurityConfigurerAdapter 을 통해서 상속 받아 사용하지만 스프링 버전업으로 지원하지 않는다
    // 하지만 테스트는 안해봤기 때문에 작동하는지는 의문 (아래의 메소드를 선언하여 사용하면 된다)
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()// csrf 기능을 비활성화
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()                                // /의 모든 요청은 누구나 접근 가능
                    .antMatchers("/admin/**").hasRole(Role.USER.name());         // /admin/의 모든 요청은 ADMIN 권한을 가지는 사용자만 접근 가능
        return http.build();
    }
}

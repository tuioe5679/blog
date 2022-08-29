package com.tuioe.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean// 해당 메소드의 리턴되는 오브젝트를 IOC로 등록
    public BCryptPasswordEncoder encoder(){ // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    // extends WebSecurityConfigurerAdapter 을 통해서 상속 받아 사용하지만 스프링 버전업으로 지원하지 않는다
    // 하지만 테스트는 안해봤기 때문에 작동하는지는 의문 (아래의 메소드를 선언하여 사용하면 된다)
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable().// csrf 기능을 비활성화
                              // csrf: 정상적인 사용자가 의도치 않은 위조요청을 보냄
                              // rest api의 서버라면 인증정보를 보관하지 않기 때문에 csrf를 설정할 필요가 없다
        authorizeHttpRequests()
                .antMatchers("/user/**").authenticated()            // /user/의 모든 요청은 인증된 사용자만 접근가능
                .antMatchers("/admin/**").hasRole("ADMIN")          // /admin/의 모든 요청은 ADMIN 권한을 가지는 사용자만 접근 가능
                .anyRequest().permitAll()                           // 그외 모든 요청은 누구나 접근 가능
                .and()
        .formLogin()
                .loginPage("/login")                                // 로그인 페이지 URL
                .loginProcessingUrl("/login")                       // 로그인 URL을 통해 시큐리티가 대신 로그인을 진행
                .defaultSuccessUrl("/user")                             // 로그인 완료시 이동할 URL
                .usernameParameter("email");                        // PrincipalDetailsService의 username 인자의 이름을 지정
                                                                    // login.html의 from에서 username이 아닌 email,id를 사용시 설정
        return http.build();
    }
}

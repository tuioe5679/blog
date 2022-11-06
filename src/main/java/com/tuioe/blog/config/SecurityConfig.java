package com.tuioe.blog.config;

import com.tuioe.blog.oauth.domain.Role;
import com.tuioe.blog.oauth.jwt.JwtAccessDeniedHandler;
import com.tuioe.blog.oauth.jwt.JwtAuthenticationEntryPoint;
import com.tuioe.blog.oauth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // extends WebSecurityConfigurerAdapter 을 통해서 상속 받아 사용하지만 스프링 버전업으로 지원하지 않는다
    // 하지만 테스트는 안해봤기 때문에 작동하는지는 의문 (아래의 메소드를 선언하여 사용하면 된다)

    //** 추후 JWT 토큰 방식 추가로 요청 권한 처리 예정
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// csrf 기능을 비활성화
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .exceptionHandling()// 예외 핸들링 객체 지정
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()                             // /의 모든 요청은 누구나 접근 가능
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }
}

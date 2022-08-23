package com.tuioe.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean// 해당 메소드의 리턴되는 오브젝트를 IOC로 등록
    public BCryptPasswordEncoder encoder(){ // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
        authorizeHttpRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
        .formLogin()
                .loginPage("/login/form")// 로그인 페이지 URL
                .loginProcessingUrl("/login")// 로그인 URL을 통해 시큐리티가 대신 로그인을 진행
                .defaultSuccessUrl("/");// 로그인 완료시 이동할 URL
        return http.build();
    }
}

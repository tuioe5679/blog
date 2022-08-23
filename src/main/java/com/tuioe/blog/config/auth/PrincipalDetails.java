package com.tuioe.blog.config.auth;

import com.tuioe.blog.Entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 /login 주소 요청이 오면 로그인을 진행
// 로그인을 진행이 완료 되면 시큐리티 session을 만들어준다 (Security ContextHolder)
// 오브젝트 타입 -> Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨
// User 오브젝트 타입 -> UserDetails 타입 객체

// Security Session -> Authentication -> UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails {

    private Member member;

    // 생성자 오버로딩
    public PrincipalDetails(Member member){
        // Member 객체 초기화
        this.member = member;
    }

    // 해당 계정의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collection;
    }

    // 비밀번호
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // 아이디 (이메일)
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    // 만료된 계정이 아닌지 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금이 아닌지 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 휴먼 계정이 아닌지 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}

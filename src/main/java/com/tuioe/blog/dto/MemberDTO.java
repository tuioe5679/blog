package com.tuioe.blog.dto;

import com.tuioe.blog.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;

    public static Member memberCreate(MemberDTO dto){
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .nickname(dto.getNickname())
                .phoneNumber(dto.getPhoneNumber())
                .role("ROLE_USER").build();

        return member;
    }
}

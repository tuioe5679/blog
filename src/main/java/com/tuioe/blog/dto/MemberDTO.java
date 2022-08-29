package com.tuioe.blog.dto;

import com.tuioe.blog.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    @Email(message = "이메일 형식으로 입력해주세요")
    @NotBlank(message = "이메일는 필수 입력 입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 입니다")
    @Length(min = 6,max = 12, message = "비밀번호는 6자 이상, 12자 이하로 입력해주세요")
    private String password;

    @NotBlank(message = "이름는 필수 항목 입니다")
    private String name;

    @NotBlank(message = "닉네임는 필수 항목 입니다")
    private String nickname;

    @NotBlank(message = "핸드폰 번호는 필수 항목 입니다")
    private String phoneNumber;

    public static Member memberCreate(MemberDTO dto){
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .nickname(dto.getNickname())
                .phoneNumber(dto.getPhoneNumber())
                .role("USER").build();

        return member;
    }
}

package com.tuioe.blog.dto;

import com.tuioe.blog.oauth.domain.Role;
import com.tuioe.blog.oauth.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String nickname;
    private String email;
    private String picture;
    private String age;

    @Builder
    public UserDto(String nickname, String email, String picture, String age) {
        this.nickname = nickname;
        this.email = email;
        this.picture = picture;
        this.age = age;
    }

    public User toEntity(){
        return User
                .builder()
                .name(nickname)
                .email(email)
                .age(age)
                .picture(picture)
                .role(Role.USER).build();
    }
}

package com.tuioe.blog.dto.oauth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaverUserTokenDto {

    private String loginToken;

    public NaverUserTokenDto(String loginToken) {
        this.loginToken = loginToken;

    }
}

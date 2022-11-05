package com.tuioe.blog.dto.oauth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {

    private String loginToken;

    public TokenDto(String loginToken) {
        this.loginToken = loginToken;

    }
}

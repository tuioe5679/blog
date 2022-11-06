package com.tuioe.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuioe.blog.dto.oauth.NaverUserTokenDto;
import com.tuioe.blog.dto.oauth.UserDto;
import com.tuioe.blog.oauth.domain.Users;
import com.tuioe.blog.oauth.domain.UserRepository;
import com.tuioe.blog.oauth.jwt.TokenProvider;
import com.tuioe.blog.oauth.jwt.dto.TokenDto;
import com.tuioe.blog.oauth.service.OauthUserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final UserRepository userRepository;
    private final OauthUserService oauthUserService;
    private final TokenProvider tokenProvider;

    public Users user;

    public TokenDto join(NaverUserTokenDto tokenDto) throws JsonProcessingException, ParseException {

        JSONParser parser = new JSONParser();

        String jsonInString = oauthUserService.loadNaverUserData(tokenDto);
        JSONObject jsonObject = (JSONObject) parser.parse(jsonInString);

        JSONObject json = (JSONObject) jsonObject.get("response");

        Users userData = userRepository.findByEmail((String)json.get("email"));

        if(userData!=null){
            user = userData;
        }
        else {
            UserDto dto = UserDto
                    .builder()
                    .email((String)json.get("email"))
                    .nickname((String)json.get("nickname"))
                    .picture((String) json.get("profile_image"))
                    .age((String) json.get("age"))
                    .build();

            Users user = dto.toEntity();
            userRepository.save(user);
            this.user = user;
        }
        return tokenProvider.tokenCreate(user);
    }
}

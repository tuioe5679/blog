package com.tuioe.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuioe.blog.dto.oauth.TokenDto;
import com.tuioe.blog.dto.oauth.UserDto;
import com.tuioe.blog.oauth.domain.User;
import com.tuioe.blog.oauth.domain.UserRepository;
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

    public User user;

    public User join(TokenDto tokenDto) throws JsonProcessingException, ParseException {

        JSONParser parser = new JSONParser();

        String jsonInString = oauthUserService.loadNaverUserData(tokenDto);
        JSONObject jsonObject = (JSONObject) parser.parse(jsonInString);

        JSONObject json = (JSONObject) jsonObject.get("response");

        User userData = userRepository.findByEmail((String)json.get("email"));

        if(userData!=null){
            user = userData;
            return userData;
        }
        else {
            UserDto dto = UserDto
                    .builder()
                    .email((String)json.get("email"))
                    .nickname((String)json.get("nickname"))
                    .picture((String) json.get("profile_image"))
                    .age((String) json.get("age"))
                    .build();

            User user = dto.toEntity();
            return userRepository.save(user);
        }
    }
}

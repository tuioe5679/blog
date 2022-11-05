package com.tuioe.blog.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuioe.blog.dto.oauth.TokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OauthUserService {

    public String loadNaverUserData(TokenDto loginToken) throws JsonProcessingException {

        // 네이버 유저 정보 요청 URL
        String url = "https://openapi.naver.com/v1/nid/me";
        // Response Data
        String jsonInString;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        // Header 정보
        httpHeaders.add("Authorization","Bearer"+" "+loginToken.getLoginToken());

        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, httpEntity, Object.class);

        ObjectMapper mapper = new ObjectMapper();
        jsonInString = mapper.writeValueAsString(resultMap.getBody());

        return jsonInString;
    }
}

package com.tuioe.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuioe.blog.dto.TokenDto;
import com.tuioe.blog.dto.UserDto;
import com.tuioe.blog.oauth.domain.User;
import com.tuioe.blog.oauth.domain.UserRepository;
import com.tuioe.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final UserRepository userRepository;

    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public ResponseEntity findMemberList(){
        return new ResponseEntity(memberService.findAllMember(), HttpStatus.OK);
    }

    @RequestMapping(value = "/member/{id}",method = RequestMethod.GET)
    public ResponseEntity findMember(@PathVariable int id){
        return new ResponseEntity(memberService.findMember(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/member/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteMember(@PathVariable int id){
        memberService.deleteMember(id);
        return new ResponseEntity("삭제완료",HttpStatus.OK);
    }

    //추후 서비스 클래스에 위임 예정
    //네이버 로그인 토큰을 받고 유정 정보 조회후 DB에 저장
    @PostMapping("/loginuser")
    public void loginUser(@RequestBody TokenDto loginToken) throws JsonProcessingException, ParseException {

        String url = "https://openapi.naver.com/v1/nid/me";
        String jsonInString = "";

        HashMap<String, Object> result = new HashMap<String, Object>();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        header.add("Authorization","Bearer"+" "+loginToken.getLoginToken());

        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

        ObjectMapper mapper = new ObjectMapper();
        jsonInString = mapper.writeValueAsString(resultMap.getBody());

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonInString);

        JSONObject json = (JSONObject) jsonObject.get("response");

        UserDto dto = UserDto
                .builder()
                .email((String)json.get("email"))
                .nickname((String)json.get("nickname"))
                .picture((String) json.get("profile_image"))
                .age((String) json.get("age"))
                .build();

        User user = dto.toEntity();
        userRepository.save(user);

    }
}

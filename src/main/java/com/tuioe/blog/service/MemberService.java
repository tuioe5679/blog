package com.tuioe.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuioe.blog.domain.Entity.Member;
import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.domain.repositroy.MemberRepositroy;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final MemberRepositroy memberRepositroy;
    private final UserRepository userRepository;
    private final OauthUserService oauthUserService;

    public List<MemberDTO> findAllMember(){
        List<Member> members = memberRepositroy.findAll();
        List<MemberDTO> responseDTOS = new ArrayList<>();
        for(Member member: members){ // 향상된 for문 사용 주로 배열에 사용
            responseDTOS.add(MemberDTO.create(member));
            //responseDTOS의 List 변수에 MemberDTO 생성하여 저장
        }
        return responseDTOS;
    }

    public MemberDTO findMember(int id){
        Member member = memberRepositroy.findById(id).get();
        return MemberDTO.create(member);
    }

    public void deleteMember(int id){
        Member member = memberRepositroy.findById(id).get();
        memberRepositroy.delete(member);
    }

    public User join(TokenDto tokenDto) throws JsonProcessingException, ParseException {

        JSONParser parser = new JSONParser();

        String jsonInString = oauthUserService.loadNaverUserData(tokenDto);
        JSONObject jsonObject = (JSONObject) parser.parse(jsonInString);

        JSONObject json = (JSONObject) jsonObject.get("response");

        User userData = userRepository.findByEmail((String)json.get("email"));

        if(userData!=null){
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

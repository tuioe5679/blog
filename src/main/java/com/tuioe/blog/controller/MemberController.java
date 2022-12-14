package com.tuioe.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuioe.blog.dto.oauth.NaverUserTokenDto;
import com.tuioe.blog.oauth.domain.Users;
import com.tuioe.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestBody NaverUserTokenDto loginToken) throws ParseException, JsonProcessingException {
        return new ResponseEntity(memberService.join(loginToken),HttpStatus.OK);
    }
}

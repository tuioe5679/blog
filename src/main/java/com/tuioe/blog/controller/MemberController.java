package com.tuioe.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuioe.blog.dto.oauth.TokenDto;
import com.tuioe.blog.oauth.domain.User;
import com.tuioe.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


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

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody TokenDto loginToken) throws ParseException, JsonProcessingException {
        return new ResponseEntity(memberService.join(loginToken),HttpStatus.OK);
    }
}

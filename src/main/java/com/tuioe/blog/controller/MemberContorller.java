package com.tuioe.blog.controller;

import com.tuioe.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberContorller {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public ResponseEntity findMemberList(){
        return new ResponseEntity(memberService.findAllMember(), HttpStatus.OK);
    }

    @RequestMapping(value = "/member/{id}",method = RequestMethod.GET)
    public ResponseEntity findMember(@PathVariable int id){
        return new ResponseEntity(memberService.findMember(id),HttpStatus.OK);
    }
}

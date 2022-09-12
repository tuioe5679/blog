package com.tuioe.blog.controller;

import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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

    @RequestMapping(value = "/singup",method = RequestMethod.POST)
    public ResponseEntity SingUp(@Valid @RequestBody MemberDTO dto){
        memberService.Join(dto);
        return new ResponseEntity("회원가입 완료",HttpStatus.CREATED);
    }
}

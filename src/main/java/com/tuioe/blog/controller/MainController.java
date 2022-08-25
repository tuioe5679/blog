package com.tuioe.blog.controller;

import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String getMainPage(){
        return "main.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping("/user")
    public String getUserPage(){
        return "user.html";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin.html";
    }

    @GetMapping("/singup")
    public String getSingUpPage(){
        return "singUp.html";
    }

    @PostMapping("/singup")
    public String SingUp(MemberDTO dto){
        memberService.Join(dto);
        return "redirect:/login.html";
    }
}

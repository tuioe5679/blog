package com.tuioe.blog.controller;

import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    MemberRepositroy memberRepositroy;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getMainPage(){
        return "main.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @ResponseBody
    @PostMapping("/login")
    public  String Login(){
        return "로그인 완료";
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
    public String SingUp(Member member){
        member.setRole("ROLE_USER");
        String password = member.getPassword();
        String EncPassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(EncPassword);
        memberRepositroy.save(member);
        return "redirect:/login.html";
    }
}

package com.tuioe.blog.controller;

import com.tuioe.blog.dto.MemberDTO;
import com.tuioe.blog.service.BoardService;
import com.tuioe.blog.service.CommentService;
import com.tuioe.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    private final CommentService commentService;

    private final BoardService boardService;

    @GetMapping("/")
    public String getMainPage(){
        return "main.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal){
        String username = principal.getName();
        boardService.findUserName(username);
        commentService.findUserName(username);
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
    public String SingUp(@Valid MemberDTO dto){
        memberService.Join(dto);
        return "redirect:/login.html";
    }
}

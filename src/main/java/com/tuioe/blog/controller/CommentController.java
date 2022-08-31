package com.tuioe.blog.controller;

import com.tuioe.blog.dto.CommentDTO;
import com.tuioe.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RequestMapping(value = "/comments",method = RequestMethod.GET)
    public ResponseEntity findCommentList(){
        return new ResponseEntity(commentService.findAllComment(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResponseEntity findComment(@PathVariable int id){
        return new ResponseEntity(commentService.findComment(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResponseEntity commentAdd(@RequestBody CommentDTO dto){
        commentService.createComment(dto);
        return new ResponseEntity("Create",HttpStatus.CREATED);
    }
}

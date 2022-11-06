package com.tuioe.blog.controller;

import com.tuioe.blog.dto.comment.CommentRequestDto;
import com.tuioe.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{id}")
    public ResponseEntity findCommentList(@PathVariable Long id){
        return new ResponseEntity(commentService.findAllComment(id),HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity commentAdd(@RequestBody CommentRequestDto dto){
        commentService.createComment(dto);
        return new ResponseEntity("Create",HttpStatus.CREATED);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity commentUpdate(@PathVariable int id,@RequestBody CommentRequestDto dto){
        commentService.updateComment(id,dto);
        return new ResponseEntity("Create",HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity commentDelete(@PathVariable int id){
        commentService.deleteComment(id);
        return new ResponseEntity("Delete",HttpStatus.OK);
    }

    @DeleteMapping("/comments")
    public ResponseEntity commentALlDelete(){
        commentService.allDeleteComment();
        return new ResponseEntity("Delete",HttpStatus.OK);
    }
}

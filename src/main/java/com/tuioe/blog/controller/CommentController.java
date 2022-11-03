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

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResponseEntity findCommentList(@PathVariable Long id){
        return new ResponseEntity(commentService.findAllComment(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResponseEntity commentAdd(@RequestBody CommentDTO dto){
        commentService.createComment(dto);
        return new ResponseEntity("Create",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.PUT)
    public ResponseEntity commentUpdate(@PathVariable int id,@RequestBody CommentDTO dto){
        commentService.updateComment(id,dto);
        return new ResponseEntity("Create",HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.DELETE)
    public ResponseEntity commentDelete(@PathVariable int id){
        commentService.deleteComment(id);
        return new ResponseEntity("Delete",HttpStatus.OK);
    }

    @RequestMapping(value = "/comments",method = RequestMethod.DELETE)
    public ResponseEntity commentALlDelete(){
        commentService.allDeleteComment();
        return new ResponseEntity("Delete",HttpStatus.OK);
    }
}

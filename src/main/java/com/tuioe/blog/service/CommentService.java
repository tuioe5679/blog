package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Comment;
import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.dto.CommentDTO;
import com.tuioe.blog.repositroy.CommentRepositroy;
import com.tuioe.blog.repositroy.MemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    String username;
    @Autowired
    private CommentRepositroy commentRepositroy;

    @Autowired
    private MemberRepositroy memberRepositroy;

    public List<CommentDTO> findAllComment(){
        List<Comment> comments = commentRepositroy.findAll();
        List<CommentDTO> responseDTO = new ArrayList<>();
        for(Comment comment: comments){
            responseDTO.add(CommentDTO.create(comment));
        }
        return responseDTO;
    }

    public void getUsername(String username){
        this.username = username;
    }

    public CommentDTO findComment(int id){
        return CommentDTO.create(commentRepositroy.findById(id).get());
    }

    public void createComment(CommentDTO dto){
        Member member = memberRepositroy.findByEmail(username);
        Comment comment = dto.commentCreate(dto);
        comment.setMember(member);
        commentRepositroy.save(comment);
    }
}

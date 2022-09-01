package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Comment;
import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.dto.CommentDTO;
import com.tuioe.blog.repositroy.CommentRepositroy;
import com.tuioe.blog.repositroy.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private String username;

    private final CommentRepositroy commentRepositroy;

    private final MemberRepositroy memberRepositroy;

    public List<CommentDTO> findAllComment(){
        List<Comment> comments = commentRepositroy.findAll();
        List<CommentDTO> responseDTO = new ArrayList<>();
        for(Comment comment: comments){
            responseDTO.add(CommentDTO.create(comment, comment.getMember().getNickname()));
        }
        return responseDTO;
    }

    public void findUserName(String username){
        this.username = username;
    }

    public CommentDTO findComment(int id){
        Comment comment = commentRepositroy.findById(id).get();
        return CommentDTO.create(comment,comment.getMember().getNickname());
    }

    public void createComment(CommentDTO dto){
        Comment comment = dto.commentCreate(dto);
        Member member = memberRepositroy.findByEmail(username);
        comment.setMember(member);
        commentRepositroy.save(comment);
    }
}

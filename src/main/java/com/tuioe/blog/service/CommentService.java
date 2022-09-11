package com.tuioe.blog.service;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.Entity.Comment;
import com.tuioe.blog.Entity.Member;
import com.tuioe.blog.dto.CommentDTO;
import com.tuioe.blog.repositroy.BoardRepositroy;
import com.tuioe.blog.repositroy.CommentRepositroy;
import com.tuioe.blog.repositroy.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private String username;

    private final CommentRepositroy commentRepositroy;

    private final MemberRepositroy memberRepositroy;

    private final BoardRepositroy boardRepositroy;

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
        Board board = boardRepositroy.findById(dto.getIdx()).get();
        comment.setMember(member);
        comment.setBoard(board);
        commentRepositroy.save(comment);
    }

    public void updateComment(int id,CommentDTO dto){
        Comment comment = commentRepositroy.findById(id).get();
        comment.setContent(dto.getContent());
        commentRepositroy.save(comment);
    }

    public void deleteComment(int id){
        Comment comment = commentRepositroy.findById(id).get();
        commentRepositroy.delete(comment);
    }

    public void allDeleteComment(){
        commentRepositroy.deleteAll();
    }
}

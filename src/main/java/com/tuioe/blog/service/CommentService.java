package com.tuioe.blog.service;

import com.tuioe.blog.domain.Entity.Board;
import com.tuioe.blog.domain.Entity.Comment;
import com.tuioe.blog.domain.Entity.Member;
import com.tuioe.blog.dto.CommentDTO;
import com.tuioe.blog.domain.repositroy.BoardRepositroy;
import com.tuioe.blog.domain.repositroy.CommentRepositroy;
import com.tuioe.blog.domain.repositroy.MemberRepositroy;
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

    public List<CommentDTO> findAllComment(Long id){
        Board board = boardRepositroy.findById(id).get();
        List<Comment> comments = commentRepositroy.findAllByBoard(board);
        List<CommentDTO> responseDTO = new ArrayList<>();
        for(Comment comment: comments){
            responseDTO.add(CommentDTO.create(comment, comment.getMember().getNickname()));
        }
        return responseDTO;
    }

    public void findUserName(String username){
        this.username = username;
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

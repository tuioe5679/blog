package com.tuioe.blog.service;

import com.tuioe.blog.domain.Entity.Board;
import com.tuioe.blog.domain.Entity.Comment;
import com.tuioe.blog.domain.repositroy.BoardRepositroy;
import com.tuioe.blog.domain.repositroy.CommentRepositroy;
import com.tuioe.blog.dto.comment.CommentListResponseDto;
import com.tuioe.blog.dto.comment.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepositroy commentRepositroy;

    private final BoardRepositroy boardRepositroy;

    public List<CommentListResponseDto> findAllComment(Long id){
        Board board = boardRepositroy.findById(id).get();
        List<Comment> comments = commentRepositroy.findAllByBoard(board);
        List<CommentListResponseDto> responseDTO = new ArrayList<>();
        for(Comment comment: comments){
            responseDTO.add(CommentListResponseDto.create(comment));
        }
        return responseDTO;
    }

    public void createComment(CommentRequestDto dto){
        Comment comment = dto.toEntity();
        Board board = boardRepositroy.findById(dto.getIdx()).get();
        comment.setUser(board.getUser());
        comment.setBoard(board);
        commentRepositroy.save(comment);
    }

    public void updateComment(int id,CommentRequestDto dto){
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

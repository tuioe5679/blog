package com.tuioe.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tuioe.blog.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String content;
    private LocalDateTime date;

    public static CommentDTO create(Comment comment){
        return new CommentDTO(comment.getContent(),
                              comment.getDate());
    }

    public Comment commentCreate(CommentDTO dto){
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}

package com.tuioe.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tuioe.blog.Entity.Comment;
import com.tuioe.blog.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private String content;
    private String nickname;
    private LocalDateTime date;

    public static CommentDTO create(Comment comment){
        return new CommentDTO(comment.getContent(),
                              null,
                              comment.getDate());
    }

    public Comment commentCreate(CommentDTO dto){
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}
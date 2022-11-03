package com.tuioe.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tuioe.blog.domain.Entity.Comment;
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

    private Long idx;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    public CommentDTO(String content, String nickname, LocalDateTime date) {
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }

    public static CommentDTO create(Comment comment, String nickname){
        return new CommentDTO(comment.getContent(),
                              nickname,
                              comment.getDate());
    }

    public Comment commentCreate(CommentDTO dto){
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}

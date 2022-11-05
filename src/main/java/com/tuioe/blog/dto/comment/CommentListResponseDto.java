package com.tuioe.blog.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuioe.blog.domain.Entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentListResponseDto {

    private String content;

    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    @Builder
    public CommentListResponseDto(String content, String nickname, LocalDateTime date) {
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }

    public static CommentListResponseDto create(Comment comment){
        return CommentListResponseDto.builder()
                .content(comment.getContent())
                .nickname(comment.getUser().getName())
                .date(comment.getDate())
                .build();
    }
}

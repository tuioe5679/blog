package com.tuioe.blog.dto.comment;

import com.tuioe.blog.domain.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {

    private String content;

    private String nickname;

    private Long idx;


    public Comment toEntity(){
        return Comment.builder()
               .content(content)
               .build();
    }
}

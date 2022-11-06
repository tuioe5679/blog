package com.tuioe.blog.dto.comment;

import com.tuioe.blog.domain.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;

    private Long idx;

    public Comment toEntity(){
        return Comment.builder()
               .content(content)
               .build();
    }
}

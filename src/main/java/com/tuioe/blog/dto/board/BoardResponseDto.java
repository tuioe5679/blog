package com.tuioe.blog.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuioe.blog.domain.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;

    private String content;

    private String nickname;

    private int hits;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = board.getUser().getName();
        this.hits = board.getHits();
        this.date = board.getDate();
    }
}

package com.tuioe.blog.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuioe.blog.domain.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {

    private Long idx;
    private String title;

    private String content;

    private String nickname;

    private int hits;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    public BoardListResponseDto(Board board) {
        this.idx = board.getIdx();
        this.title = board.getTitle();
        this.content = boardContentSub(board.getContent());
        this.nickname = "이름";
        this.hits = board.getHits();
        this.date = board.getDate();
    }

    public String boardContentSub(String content){
        if(!(content.length() < 150)){
            String subContent = content.substring(0,150) + "...";
            return subContent;
        }
        else {
            return content + "...";
        }
    }
}

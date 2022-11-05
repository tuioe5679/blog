package com.tuioe.blog.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuioe.blog.domain.Entity.Board;
import lombok.Builder;
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

    @Builder
    public BoardListResponseDto(Long idx, String title, String content, String nickname, int hits, LocalDateTime date) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.hits = hits;
        this.date = date;
    }

    public static BoardListResponseDto create(Board board){
        return BoardListResponseDto.builder()
                .idx(board.getIdx())
                .title(board.getTitle())
                .content(boardContentSub(board.getContent()))
                .hits(board.getHits())
                .date(board.getDate())
                .nickname(board.getUser().getName())
                .build();
    }

    public static String boardContentSub(String content){
        if(!(content.length() < 150)){
            String subContent = content.substring(0,150) + "...";
            return subContent;
        }
        else {
            return content + "...";
        }
    }
}

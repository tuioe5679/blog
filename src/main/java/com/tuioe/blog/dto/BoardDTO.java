package com.tuioe.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuioe.blog.domain.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long idx;

    private String title;

    private String content;

    private String nickname;

    private int hits;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    public static BoardDTO create(Board board,String nickname){
        return new BoardDTO(
                            board.getIdx(),
                            board.getTitle(),
                            board.getContent(),
                            nickname,
                            board.getHits(),
                            board.getDate());
    }

    public static Board boardCreate(BoardDTO dto){
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .hits(0).build();
    }

    public String boardContentSub(Board board){
        String boardContent = board.getContent();
        if(!(boardContent.length() < 150)){
            String subContent = board.getContent().substring(0,150) + "...";
            return subContent;
        }
        else {
            return boardContent + "...";
        }
    }
}

package com.tuioe.blog.dto;

import com.tuioe.blog.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private String title;
    private String content;
    private String name;
    private LocalDateTime date;

    public static BoardDTO create(Board board){
        return new BoardDTO(board.getTitle(),board.getContent(),
                            board.getName(),board.getDate());
    }

    public static Board boardCreate(BoardDTO dto){
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .name(dto.getName())
                .hits(0).build();
    }
}

package com.tuioe.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tuioe.blog.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDTO {
    private String title;
    private String content;
    private String name;
    private LocalDate date;

    public static BoardDTO create(Board board){
        return new BoardDTO(board.getTitle(),board.getContent(),
                            board.getName(),board.getDate());
    }
}

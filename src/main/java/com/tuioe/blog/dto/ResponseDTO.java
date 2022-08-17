package com.tuioe.blog.dto;

import com.tuioe.blog.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String title;
    private String content;
    private String name;
    private LocalDate date;

    public static ResponseDTO create(Board board){
        return new ResponseDTO(board.getTitle(),board.getContent(),
                               board.getName(),board.getDate());
    }
}

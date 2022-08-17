package com.tuioe.blog.Entity;

import com.tuioe.blog.dto.RequestDTO;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "board") //테이블 이름 지정
@Entity //Entity 클래스로 지정 (데이터베이스와 매핑)
@Data //lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int hits;

    public static Board create(RequestDTO DTO){
        Board board = new Board();
        board.title = DTO.getTitle();
        board.content = DTO.getContent();
        board.name = DTO.getName();
        board.date = LocalDate.now();
        board.hits = 0;
        return board;
    }
}

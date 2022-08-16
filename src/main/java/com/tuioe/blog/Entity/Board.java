package com.tuioe.blog.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity //Entity 클래스로 지정 (데이터베이스와 매핑)
@Data //lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int hits;
}

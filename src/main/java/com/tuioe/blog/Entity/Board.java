package com.tuioe.blog.Entity;

import com.tuioe.blog.dto.BoardDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "board") //테이블 이름 지정
@Entity //Entity 클래스로 지정 (데이터베이스와 매핑)
@Data //lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
@EntityListeners(AuditingEntityListener.class)// 엔티티 리스너를 적용 (Auditing 기능을 포함시킨다) @CreateDate 기능 사용시 필요
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,length = 3000)
    private String content;

    @Column(nullable = false,length = 10)
    private String name;

    @Column(nullable = false)
    @CreatedDate// Entity가 생성되어 저장할때 현재 시간을 자동 생성한다
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="member_nickname")
    private Member member;

    @Column(nullable = false)
    private int hits;

    public static Board create(BoardDTO DTO){
        Board board = new Board();
        board.title = DTO.getTitle();
        board.content = DTO.getContent();
        board.name = DTO.getName();
        board.hits = 0;
        return board;
    }
}

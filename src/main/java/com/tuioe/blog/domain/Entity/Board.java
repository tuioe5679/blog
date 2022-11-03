package com.tuioe.blog.domain.Entity;

import com.tuioe.blog.dto.BoardDTO;
import com.tuioe.blog.dto.board.BoardRequestDto;
import com.tuioe.blog.dto.board.BoardResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "board") // 테이블 이름 지정
@Entity // Entity 클래스로 지정 (데이터베이스와 매핑)
@Data // lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)// 엔티티 리스너를 적용 (Auditing 기능을 포함시킨다) @CreateDate 기능 사용시 필요
public class Board {

    @Id// PK 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본키 생성 전략을 DB에게 위임(MySQL = Auto INCREMENT)
    private Long idx;

    @Column(nullable = false,length = 100)// null X 길이는 100
    private String title;

    @Column(nullable = false,length = 5000)// null X 길이는 5000
    private String content;

    @Column(nullable = false)// null X
    @CreatedDate// Entity가 생성되어 저장할때 현재 시간을 자동 생성한다
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)// 연관관계를 지정 n:1 관계를 설정한다
    @JoinColumn(name="member_id")// 외래키를 지정
    private Member member;

    @Column(nullable = false)// null X
    private int hits;

    @Builder //Builder 패턴
    public Board(Long idx, String title, String content, LocalDateTime date, Member member, int hits) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.date = date;
        this.member = member;
        this.hits = hits;
    }

    public void update(BoardRequestDto dto){
        if(dto.getTitle()!=""){
            this.title = dto.getTitle();
        }
        if(dto.getContent()!=""){
            this.content = dto.getContent();
        }
    }
}
package com.tuioe.blog.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "comment") // 테이블 이름 지정
@Entity // Entity 클래스로 지정 (데이터베이스와 매핑)
@Data // lombok의 어노테이션 (get,set,toString 메소드를 자동 생성)
@EntityListeners(AuditingEntityListener.class)// 엔티티 리스너를 적용 (Auditing 기능을 포함시킨다) @CreateDate 기능 사용시 필요
public class Comment {

    @Id// PK 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본키 생성 전략을 DB에게 위임(MySQL = Auto INCREMENT)
    private Integer id;

    @ManyToOne// 연관관계를 지정 n:1 관계를 설정한다
    @JoinColumn(name="nickname")// 외래키를 지정 FK member의 PK를 외래키로 지정
    private Member member;

    @Column(nullable = false,length = 1000)// null X 길이는 1000
    private String content;

    @Column(nullable = false)// null X
    @CreatedDate
    private LocalDateTime date;

}

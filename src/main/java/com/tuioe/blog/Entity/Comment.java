package com.tuioe.blog.Entity;

import com.tuioe.blog.dto.BoardDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "comment")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(nullable = false,length = 1000)
    private String content;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate date;

}

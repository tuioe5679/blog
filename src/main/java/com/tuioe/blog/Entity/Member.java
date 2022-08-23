package com.tuioe.blog.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "member")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer idx;

    @Column(nullable = false,length = 30)
    private String email;

    @Column(nullable = false,length = 300)
    private String password;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(nullable = false,length = 30)
    private String nickname;

    @Column(nullable = false,length = 15)
    private String phoneNumber;

    @Column(nullable = false,length = 10)
    private String role;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
}

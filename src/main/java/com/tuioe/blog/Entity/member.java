package com.tuioe.blog.Entity;

import lombok.Data;
import javax.persistence.*;

@Table(name = "member")
@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer idx;

    @Column(nullable = false,length = 15)
    private String id;

    @Column(nullable = false,length = 300)
    private String password;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(name = "member_nickname",nullable = false,length = 30)
    private String nickname;

    @Column(nullable = false,length = 15)
    private String phoneNumber;

    @Column(nullable = false,length = 30)
    private String email;
}

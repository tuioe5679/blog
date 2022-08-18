package com.tuioe.blog.Entity;

import lombok.Cleanup;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "comment")
@Entity
@Data
public class comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate date;

}

package com.tuioe.blog.repositroy;

import com.tuioe.blog.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepositroy<DB 연결 클래스,PK 자료형>
public interface BoardRepositroy extends JpaRepository<Board, Integer> {
}

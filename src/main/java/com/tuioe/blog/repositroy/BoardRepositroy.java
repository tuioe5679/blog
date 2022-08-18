package com.tuioe.blog.repositroy;

import com.tuioe.blog.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//JpaRepositroy<DB 연결 클래스,PK 자료형>
public interface BoardRepositroy extends JpaRepository<Board, Integer> {
    @Modifying
    @Query("update Board p set p.hits = p.hits + 1 where p.idx = :idx")
    void updateHits(@Param(value = "idx") Integer idx);
}

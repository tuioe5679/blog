package com.tuioe.blog.domain.repositroy;

import com.tuioe.blog.domain.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//JpaRepositroy<DB 연결 클래스,PK 자료형>
public interface BoardRepositroy extends JpaRepository<Board, Long> {
    @Modifying//@Query 어노테이션으로 SELECT를 제외한 모든 쿼리에 사용되는 어노테이션
    @Query("update Board p set p.hits = p.hits + 1 where p.idx = :idx")
    void updateHits(@Param("idx") Long idx);
}

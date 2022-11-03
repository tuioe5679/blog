package com.tuioe.blog.domain.repositroy;

import com.tuioe.blog.domain.Entity.Board;
import com.tuioe.blog.domain.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepositroy extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBoard(Board board);
}

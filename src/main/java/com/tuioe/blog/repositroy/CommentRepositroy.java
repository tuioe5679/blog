package com.tuioe.blog.repositroy;

import com.tuioe.blog.Entity.Board;
import com.tuioe.blog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepositroy extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBoard(Board board);
}

package com.tuioe.blog.repositroy;

import com.tuioe.blog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositroy extends JpaRepository<Comment, Integer> {
}

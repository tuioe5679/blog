package com.tuioe.blog.service;

import com.tuioe.blog.repositroy.CommentRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepositroy commentRepositroy;
}

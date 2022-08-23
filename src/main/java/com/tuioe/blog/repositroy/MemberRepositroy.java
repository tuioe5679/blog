package com.tuioe.blog.repositroy;

import com.tuioe.blog.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositroy extends JpaRepository<Member, Integer> {
}

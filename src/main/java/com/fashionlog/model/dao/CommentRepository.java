package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}

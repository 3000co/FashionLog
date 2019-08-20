package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Post;


public interface TestRepository extends JpaRepository<Comment, Integer> {

}

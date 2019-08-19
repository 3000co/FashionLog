package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}

package com.fashionlog.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

}


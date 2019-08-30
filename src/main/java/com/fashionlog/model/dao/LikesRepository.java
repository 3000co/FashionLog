package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Post;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
	
	public Long countByPostNo(Post post);
	
}

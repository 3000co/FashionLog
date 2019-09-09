package com.fashionlog.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
	
	public Long countByPostNo(Post post);
	
	public Optional<Likes> findByMemberNoAndPostNo(Member member, Post post);
	
	
}

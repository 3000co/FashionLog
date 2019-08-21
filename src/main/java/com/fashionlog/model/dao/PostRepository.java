package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByMemberNo(int memberNo);
	public Post deleteByPostNoAndMemberNo(int postNo, int memberNo);
	public List<Post> findTop3ByPostNoAndMemberNoOrderByUploadTimeDesc(int postNo, int memberNo);
}

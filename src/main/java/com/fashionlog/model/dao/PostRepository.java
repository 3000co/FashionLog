package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByMemberNo(int memberNo);
	Post deleteByPostNoAndMemberNo(int postNo, int memberNo);
	List<Post> findTop3ByPostNoAndMemberNoOrderByUploadTimeDesc(int postNo, int memberNo);
}

package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByMemberNo(Member memberNo);
	public Post deleteByPostNoAndMemberNo(Post postNo, Member memberNo);
	public List<Post> findTop3ByPostNoAndMemberNoOrderByUploadTimeDesc(Post postNo, Member memberNo);
	public List<Post> findTop5ByMemberNoOrderByUploadTimeDesc(Member followee);
}


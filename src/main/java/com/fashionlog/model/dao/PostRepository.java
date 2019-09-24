package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.dto.Style;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByMemberNo(Member memberNo);
	public Post deleteByPostNoAndMemberNo(Post postNo, Member memberNo);
	public List<Post> findTop3ByPostNoAndMemberNoOrderByUploadTimeDesc(Post postNo, Member memberNo);
	public List<Post> findByMemberNoOrderByUploadTimeDesc(Member member, Pageable paging);
	public List<Post> findByStyleNo1OrderByUploadTimeDesc(Style styleNo, Pageable paging);
	public List<Post> findByStyleNo2OrderByUploadTimeDesc(Style styleNo, Pageable paging);
	public List<Post> findByStyleNo3OrderByUploadTimeDesc(Style styleNo, Pageable paging);
	public Integer countByMemberNo(Member userInfo);
}


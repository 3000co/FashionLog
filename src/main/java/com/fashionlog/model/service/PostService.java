package com.fashionlog.model.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface PostService {
	
	//팔로이 글 5개
	public Set<Post> getFeedByFollowee(Member user, Pageable paging);

	//내글 5개
	public Set<Post> getFeedByMe(Member user, Pageable paging);
	
	//스타일 글 5개
	public Set<Post> getFeedByStyle(Member user, Pageable paging);
	
	public Set<Post> getAllFeed(Pageable paging);
	
	public List<Post> getPostToFeed(Member user, Pageable paging);

	public List<Post> getProfileFeed(Member userInfo, Pageable paging);
	

	

}
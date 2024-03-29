package com.fashionlog.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface LikesService {
	
	public Long doLike(Member member, Post post);

	public Long unLike(Member member, Post post) throws NoSuchElementException;

	public void countLikes();
	
	public void countLikes(Post post);

	public List<Post> setLikeCount(List<Post> posts);
	
}

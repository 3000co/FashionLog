package com.fashionlog.model.service;

import java.util.NoSuchElementException;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface LikesService {

	public int doLike(Member member, Post post);

	public int unLike(Member member, Post post) throws NoSuchElementException;

	public void countLikes();
	public void countLikes(Post post);
	
}

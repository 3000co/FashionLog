package com.fashionlog.model.service;

import java.util.NoSuchElementException;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface LikesService {

	int doLike(Member member, Post post);

	int unLike(Member member, Post post) throws NoSuchElementException;

}

package com.fashionlog.model.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dto.File;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

public interface PostService {
	
	public File insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception;

	public void countLikes();
	//팔로이 글 5개
	Map<Integer, Post> getFeedByFollowee(Member user, Pageable paging);

	//내글 5개
	Map<Integer, Post> getFeedByMe(Member user, Pageable paging);
	
	//스타일 글 5개
	Map<Integer, Post> getFeedByStyle(Member user, Pageable paging);
	
	
	Map<Integer, Post> getAllFeed(Pageable paging);

	

}
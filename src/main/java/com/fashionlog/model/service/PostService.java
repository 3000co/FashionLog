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
	
	public void countLikes();

	Map<Integer, Post> getFeedByFollowee(Member user, Pageable paging);

}
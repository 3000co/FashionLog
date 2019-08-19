package com.fashionlog.controller;

import java.util.Date;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Post;

@RestController
public class PostController {
	@Autowired
	private PostRepository postRepository;
	
	@RequestMapping("/add")
	public Post insertPost() {
		Post post = new Post();
		post.setMemberNo(3);
		post.setUploadTime(new Date());
		post.setPostImageNo(1);
		post.setContents("테스트로 작성한 포스트 내용");
		post.setStyleNo1(1);
		post.setStyleNo2(1);
		
		post = postRepository.save(post);
		return post;
	}
}

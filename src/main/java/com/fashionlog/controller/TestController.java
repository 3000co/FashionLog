package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dao.TestRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Post;

@RestController
public class TestController {
	@Autowired
	private TestRepository testRepository;
	
//	@RequestMapping("/add")
//	public Post insertPost() {
//		Post post = new Post();
//		post.setMemberNo(3);
//		post.setUploadTime(new Date());
//		post.setPostImageNo(1);
//		post.setContents("테스트로 작성한 포스트 내용");
//		post.setStyleNo1(1);
//		post.setStyleNo2(1);
//		
//		post = testRepository.save(post);
//		return post;
//	}
	
	@RequestMapping("/joinTest")
	public List<Comment> joinTest() {
		
		List<Comment> commentList =testRepository.findAll();
		
		return commentList;
	}
	
	@RequestMapping("/test1")
	public List<Object[]> queryTest() {
		
		List<Object[]> commentList = testRepository.test1();
		for(Object[] row : commentList) {
			System.out.println(Arrays.toString(row));
		}
		
		return commentList;
	}
	
	
}

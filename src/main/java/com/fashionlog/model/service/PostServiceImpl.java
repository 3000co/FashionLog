package com.fashionlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LikesRepository likesRepository;
	
	
	@Override
	public void countLikes() {
		List<Post> posts = postRepository.findAll();
		for (Post post:posts) {
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(post);
			System.out.println("post likes count : " + post);
		}
		
	}
	
	
}

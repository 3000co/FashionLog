package com.fashionlog.model.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

@Service
public class LikesServiceImpl implements LikesService{
	
	@Autowired
	LikesRepository likesRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	NotificationService notificationService;
	
	@Override
	public int doLike(Member member, Post post) {
		Optional<Likes> likeEvent = likesRepository.findByMemberNoAndPostNo(member, post);
		if(likeEvent.isPresent()) {
			return likeEvent.get().getLikesNo();
		}else {
			Likes newLikes = new Likes();
			newLikes.setMemberNo(member);
			newLikes.setPostNo(post);
			likesRepository.save(newLikes);
			countLikes(post);
			notificationService.enrollNotification(newLikes);
			return newLikes.getPostNo().getPostNo();
		}
	}
	
	/**
	 * @return unliked post number
	 */
	@Override
	public int unLike(Member member, Post post) throws NoSuchElementException{
		Optional<Likes> likeEvent = likesRepository.findByMemberNoAndPostNo(member, post);
			Likes deletedLikes = likeEvent.get();
			int unlikedPostNo = deletedLikes.getPostNo().getPostNo();
			likesRepository.delete(deletedLikes);
			countLikes(post);
			return unlikedPostNo;
	}

	@Override
	public void countLikes() {
		List<Post> posts = postRepository.findAll();
		for (Post post : posts) {
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(post);
			System.out.println("post no : "+post.getPostNo()+"likesCount : "+post.getLikesCount());
		}
		System.out.println("post counted");
	}
	
	@Override
	public void countLikes(Post post) {
		Post p = postRepository.findById(post.getPostNo()).get();
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(p);
		}

}

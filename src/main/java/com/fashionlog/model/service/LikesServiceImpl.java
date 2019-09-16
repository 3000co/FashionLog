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
public class LikesServiceImpl implements LikesService {

	@Autowired
	LikesRepository likesRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	NotificationService notificationService;

	@Override
	public Long doLike(Member member, Post post) {
		Likes newLikes = new Likes();
		newLikes.setMemberNo(member);
		newLikes.setPostNo(post);
		likesRepository.save(newLikes);
		countLikes(post);
		notificationService.enrollNotification(newLikes);
		return post.getLikesCount();
	}

	/**
	 * @return unliked post like count
	 */
	@Override
	public Long unLike(Member member, Post post) throws NoSuchElementException {
		Optional<Likes> likeEvent = likesRepository.findByMemberNoAndPostNo(member, post);
		Likes deletedLikes = likeEvent.get();
		likesRepository.delete(deletedLikes);
		countLikes(post);
		return post.getLikesCount();
	}

	@Override
	public void countLikes() {
		List<Post> posts = postRepository.findAll();
		for (Post post : posts) {
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(post);
		}
	}

	@Override
	public void countLikes(Post post) {
		Post p = postRepository.findById(post.getPostNo()).get();
		if (p.getLikesCount() == null) {
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(p);
		}
	}

}

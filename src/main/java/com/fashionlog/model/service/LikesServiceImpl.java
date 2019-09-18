package com.fashionlog.model.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

@Service
public class LikesServiceImpl implements LikesService{
	
	@Autowired
	LikesRepository likesRepository;
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
			return unlikedPostNo;
	}

}

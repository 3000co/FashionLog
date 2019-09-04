package com.fashionlog.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.FollowRepository;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepository followRepository;
	
	@Override
	public boolean isFollowing(Member user, Member followee) {
		Optional<Follow> followEvent = followRepository.findByFollowerMemNoAndFolloweeMemNo(user,followee);
		return followEvent.isPresent();
	}

	@Override
	public boolean isFollowed(Member user, Member follower) {
		Optional<Follow> followEvent = followRepository.findByFollowerMemNoAndFolloweeMemNo(follower,user);
		return followEvent.isPresent();
	}

}

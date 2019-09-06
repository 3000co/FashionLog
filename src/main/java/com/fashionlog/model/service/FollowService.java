package com.fashionlog.model.service;

import com.fashionlog.model.dto.Member;

public interface FollowService {
	
	public boolean isFollowing(Member user, Member followee);

	public boolean isFollowed(Member user, Member follower);
}

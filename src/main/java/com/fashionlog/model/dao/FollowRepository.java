package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Follow;



public interface FollowRepository extends JpaRepository<Follow, Integer>{
	public Follow findByFollowerMember(int FollowerMember);
	
	public Follow findByFolloweeMember(int FolloweeMember);
}

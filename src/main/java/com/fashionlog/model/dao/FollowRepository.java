package com.fashionlog.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;



public interface FollowRepository extends JpaRepository<Follow, Integer>{
	public List<Follow> findByFollowerMemNo(Member member);
	
	public List<Follow> findByFolloweeMemNo(Member member);
	
	public Optional<Follow> findByFollowerMemNoAndFolloweeMemNo(Member follower, Member followee);
}
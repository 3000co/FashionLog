package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Follow;



public interface FollowRepository extends JpaRepository<Follow, Integer>{
	public List<Follow> findByFollowNo(int followNo);	
	public List<Follow> findByFollowerMemNo(int FollowerMemNo);
	public List<Follow> findByFolloweeMemNo(int FolloweeMemNo);
	public List<Follow> findByFollowTime(char FollowTime);
}
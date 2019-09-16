package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Member findByIdAndPassword(String Id, String Password);
	
	public Member findByPassword(String Password);

	public Member findById(String id);
	
	public Member findByMemberNo(int memNo);
}


package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

	public Member findByIdAndPassword(String Id, String Password);
	
	public Member findByPassword(String Password);

	
	
}


package com.fashionlog.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
	
}

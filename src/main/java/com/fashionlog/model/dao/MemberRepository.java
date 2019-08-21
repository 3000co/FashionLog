package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
	public Member findById(String Id);
}


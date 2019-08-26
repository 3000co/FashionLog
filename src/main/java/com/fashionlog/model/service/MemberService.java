package com.fashionlog.model.service;

import java.util.List;
import java.util.Optional;

import com.fashionlog.model.dto.Member;

public interface MemberService {
	
	List<Member> findById(String Id);

	Member getMemberInfo(Member member);

	void doJoin(Member member);

	
	
}

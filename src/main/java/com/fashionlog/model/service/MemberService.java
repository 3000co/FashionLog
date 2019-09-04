package com.fashionlog.model.service;

import java.util.List;
import java.util.Optional;

import com.fashionlog.model.dto.Member;

public interface MemberService {
	
	Member findByIdAndPassword(String Id, String Password);

//	Member getMemberInfo(Member member);

	void doJoin(Member member);
	
	public void countLikes();
	
}

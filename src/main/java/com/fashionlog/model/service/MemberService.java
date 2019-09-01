package com.fashionlog.model.service;

import com.fashionlog.model.dto.Member;

public interface MemberService {
	
	Member findByIdAndPassword(String Id, String Password);

//	Member getMemberInfo(Member member);

	void doJoin(Member member);
	
	public void countLikes();
	
}

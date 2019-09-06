package com.fashionlog.model.service;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;

public interface MemberService {
	
	Member findByIdAndPassword(String Id, String Password);
	
	Member findByPassword(String Password);
	
	Member findById(String Id);
	
	Style findById(int styleNo);
	
	void doJoin(Member member);

	void doLogout(Member member);

	public void countLikes();
	
}

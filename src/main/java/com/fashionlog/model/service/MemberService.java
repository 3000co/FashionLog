package com.fashionlog.model.service;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;

public interface MemberService {
	
	Member findById(String Id);
	
	Style findById(int styleNo);
	
	void doJoin(Member member);

	void doLogout(Member member);

	public void countLikes();

}

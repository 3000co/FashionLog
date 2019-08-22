package com.fashionlog.model.service;

import java.util.Optional;

import com.fashionlog.model.dto.Member;

public interface MemberService {
	Optional<Member> findById(char Id);

	Member getMemberInfo(Member member);

	void doJoin(Member member);

}

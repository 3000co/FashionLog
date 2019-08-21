package com.fashionlog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member findById(String Id) {
		return memberRepo.findById(Id);
	}
	
}

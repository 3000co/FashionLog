package com.fashionlog.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Optional<Member> findById(char Id) {
		return memberRepo.findById(Id);
	}

	@Override
	public Member getMemberInfo(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if (findMember.isPresent()) 
			return findMember.get();
		else return null;
	}

	@Override
	public void doJoin(Member member) {
		memberRepo.setJoin(member);
		
	}
	
}

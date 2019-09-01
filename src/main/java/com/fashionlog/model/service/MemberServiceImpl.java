package com.fashionlog.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member findByIdAndPassword(String Id, String Password) {
		Member mem = memberRepo.findByIdAndPassword(Id, Password);
		memberRepo.save(mem);
		return mem;
	}

//	@Override
//	public Member getMemberInfo(Member member) {
//		String findMember = memberRepo.findById(member.getId());
//		
//		return member;
//	}

	@Override
	public void doJoin(Member member) {
		memberRepo.save(member);
	}
	
	@Override
	public void countLikes() {
		List<Member> members = memberRepo.findAll();
		for (Member member:members) {
			member.setLikesCount();
			memberRepo.save(member);
			System.out.println("member likes counted : " + member);
		}

	}
}

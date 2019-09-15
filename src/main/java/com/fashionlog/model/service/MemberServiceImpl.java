package com.fashionlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;


@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private StyleRepository styleRepo;

	@Override
	public Member findByIdAndPassword(String Id, String Password) {
		Member mem = memberRepo.findByIdAndPassword(Id, Password);
		memberRepo.save(mem);
		return mem;
	}

	@Override
	public Member findByPassword(String Password) {
		return memberRepo.findByPassword(Password);
	}
	
	@Override
	public Member findById(String Id) {
		return memberRepo.findById(Id);
	}
	
	@Override
	public Style findById(int styleNo) {
		return styleRepo.findById(styleNo);
	}
		
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

	@Override
	public void doLogout(Member member) {
		
	}
	
}

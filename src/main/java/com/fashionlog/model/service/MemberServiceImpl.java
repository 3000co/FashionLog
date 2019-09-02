package com.fashionlog.model.service;

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
		return memberRepo.findByIdAndPassword(Id, Password);
	}

	@Override
	public Member findByPassword(String Password) {
		return memberRepo.findByPassword(Password);
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
	public void doLogout(Member member) {
		
	}
	
	

}

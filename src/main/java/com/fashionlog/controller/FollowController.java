package com.fashionlog.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fashionlog.model.dao.FollowRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;

@SessionAttributes("member")
@Controller
public class FollowController {
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	// 
	@RequestMapping("/checkFollow")
	@ResponseBody
	public String checkFollow(Integer memberNo, HttpSession session) {
		//가상 세션 데이터 , 6이 로그인 해있음.
//		Member mockUser = memberRepository.findById(5).get();
//		session.setAttribute("member", mockUser);
//		
		Member user = (Member) session.getAttribute("member");
		System.out.println(user);
		if(memberNo == user.getMemberNo()) return "self";
		Optional<Follow> isFollowed = followRepository.findByFollowerMemNoAndFolloweeMemNo(user, memberRepository.findByMemberNo(memberNo));
		return isFollowed.isPresent() ? "following" : "follow";
	}
	
	public String follow(Integer memberNo, HttpSession session)	{
		return "";
	}
}


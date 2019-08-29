package com.fashionlog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.service.MemberService;

@SessionAttributes("member")
@Controller
public class FollowController {
	@Autowired
	MemberService memberService;

	// 로그인
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public String doLogin(Member member, HttpSession session) throws Exception {
		System.out.println("아이디: "+member.getId()+ " 비밀번호: "+member.getPassword());

//		Member getMemberInfo = memberService.getMemberInfo(member);
		Member getMemberInfo =memberService.findByIdAndPassword(member.getId(), member.getPassword());
		System.out.println("getMemberInfo: "+getMemberInfo);
		
		if (getMemberInfo == null) {
			session.setAttribute("member", null);
			return "login";
		} else {
			session.setAttribute("member", getMemberInfo);
			System.out.println("로그인 성공" + getMemberInfo);
			return "redirect:/";
		}
	}

	@RequestMapping("/join")
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/join.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String signupProcess(Member member, HttpSession session) {
		System.out.println("아이디: "+member.getId()+ " 비밀번호: "+member.getPassword());
		System.out.println("Member::" + member);
		memberService.doJoin(member);
		
		return "redirect:login";
	}
}


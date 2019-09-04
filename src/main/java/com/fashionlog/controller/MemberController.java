package com.fashionlog.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;
import com.fashionlog.model.service.MemberService;

@SessionAttributes("member")
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/")
	public String main() {
		return "main";
	}

	// 로그인 화면
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}

	// 로그인 처리
	@RequestMapping(value = "/login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doLogin(Member member, HttpSession session) {
		System.out.println("아이디: " + member.getId() + " 비밀번호: " + member.getPassword());
		Member getMemberInfo = memberService.findByIdAndPassword(member.getId(), member.getPassword());
		System.out.println("getMemberInfo: " + getMemberInfo);

		if (getMemberInfo == null) {
			session.setAttribute("member", null);
			return "member/login";
		} else {
			session.setAttribute("member", getMemberInfo);
			session.setAttribute("id", member.getId());
			System.out.println("로그인 성공" + getMemberInfo);
			return "redirect:/";
		}
	}

	// 로그아웃 처리
	@RequestMapping("/logout.do")
	public String doLogout(Member member, HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// 회원가입 화면
	@RequestMapping("/join")
	public String join() {
		return "member/join";
	}
	// 회원가입 처리
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String doJoin(Member member, HttpSession session) {
		System.out.println("아이디: " + member.getId() + " 비밀번호: " + member.getPassword());
		System.out.println("Member1::" + member);
		return "member/styleSelect";
	}
	
	// 회원가입 스타일 처리
		@RequestMapping(value = "/styleSelect.do", method = RequestMethod.POST)
		public String doStyleSelect(Member member, HttpSession session) {
			System.out.println(" 스타일 번호: " + member.getStyleNo1());
			System.out.println("Member2::" + member);
			int getStyleInfo = member.getStyleNo1();
			if (getStyleInfo == 0) {
				session.setAttribute("style", null);
				return "member/styleSelect";
			} else {
				memberService.doJoin(member);
				System.out.println("회원가입 성공" + getStyleInfo);
				return "redirect:/login";
			}
		}
		
	// 비밀번호 변경 화면
	@RequestMapping("/modPassword")
	public String modPassword() {
		return "member/modPassword";
	}

	// 비밀번호 변경 처리
	@RequestMapping(value = "/modPassword.do", method = RequestMethod.POST)
	public String doModPassword(Member member, HttpSession session) {
		
			return "redirect:/";
		
	}
	

	// 마이프로필 화면
	@RequestMapping("/profile")
	public String profileSetting() {
		return "member/profile";
	}
	
	//프로필 화면
	@RequestMapping("/modProfile")
	public String modProfile() {
		return "member/modProfile";
	}	
	// 프로필 변경
		@RequestMapping(value = "/modProfile.do", method = RequestMethod.POST)
		public String doModProfile(Member member, HttpSession session) {
			
			return "redirect:/profile";
		}
		
		
		
		
		
		
}

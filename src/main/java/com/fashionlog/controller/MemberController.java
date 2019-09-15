package com.fashionlog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;
import com.fashionlog.model.service.MemberService;

@Controller
public class MemberController {
	Member newMember = new Member();
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * 개발 편의를 위한 현재 맴버리스트 출력 메서드
	 * @return
	 */
	@RequestMapping(value = "/getAllMember")
	@ResponseBody
	public String getAllMember(Model model) {
		List<Member> memList = memberRepository.findAll();
		String print = "";
		for(Member mem:memList) {
			print += (mem.getId() +"<br>");
		}
		return print;
	}
	
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

			session.setAttribute("nickname", getMemberInfo.getNickname());
			session.setAttribute("id", getMemberInfo.getId());

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
		newMember.setId(member.getId());
		newMember.setPassword(member.getPassword());
		newMember.setNickname(member.getNickname());
		newMember.setPhonenumber(member.getPhonenumber());
		newMember.setEmail(member.getEmail());
		return "member/styleSelect";
	}
	
	// 회원가입 스타일 처리
		@RequestMapping(value = "/styleSelect.do", method = RequestMethod.POST)
		public String doStyleSelect(Member member, HttpSession session) {
			newMember.setStyleNo1(member.getStyleNo1());
			newMember.setStyleNo2(member.getStyleNo2());
			newMember.setStyleNo3(member.getStyleNo3());
			System.out.println(newMember);
			Style getStyleInfo = member.getStyleNo1();
			if (getStyleInfo.getStyleNo() == 0) {
				session.setAttribute("style", null);
				return "member/styleSelect";
			} else {
				memberService.doJoin(newMember);
				System.out.println("회원가입 성공" + getStyleInfo);
				return "redirect:/login";
			}
		}
		
	//이미지 선택형 스타일 선택 
		@RequestMapping(value="/styleSelectByImg", method=RequestMethod.GET)
		public String doStyleSelctByImg() {
			
			return "/member/styleSelectByImage";
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

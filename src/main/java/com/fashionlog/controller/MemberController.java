package com.fashionlog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dao.Member2Repository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.File;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Member2;
import com.fashionlog.model.dto.Role;
import com.fashionlog.model.dto.Style;
import com.fashionlog.model.service.MemberService;

@Controller
public class MemberController {
	Member newMember = new Member();
	@Autowired
	MemberService memberService;
	@Autowired
	private PasswordEncoder encoder;
	
	
	///////jaebum///////
	@Autowired
	private FileRepository fileRepo;
	@Autowired
	private StyleRepository styleRepo;
	@Autowired
	private Member2Repository member2Repo;
	
	@RequestMapping("/createAdmin")
	@ResponseBody
	public String createAdmin() {
		Member2 member = new Member2();
		member.setId("admin");
		member.setPassword(encoder.encode("admin"));
		member.setRole(Role.ROLE_ADMIN);
		member.setNickname("권권권");
		member.setPhonenumber("01012345678");
		member.setEmail("jaeb@hanmail.net");
//		File file = fileRepo.findById(2).get();
//		member.setProfileImageNo(file);
		Style style = styleRepo.findById(1);
		member.setStyleNo1(style);
		member2Repo.save(member);
		return "어드민생성";
	}
	///////////////////////////
	
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
		
	// 비밀번호 변경 화면
	@RequestMapping("/modPassword")
	public String modPassword() {
		return "user/modPassword";
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
		return "user/modProfile";
	}	
	// 프로필 변경
		@RequestMapping(value = "/modProfile.do", method = RequestMethod.POST)
		public String doModProfile(Member member, HttpSession session) {
			
			return "redirect:/profile";
		}
		
		
		
}

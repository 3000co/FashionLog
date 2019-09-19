package com.fashionlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.File;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Style;
import com.fashionlog.model.service.MemberService;
@Controller
public class MemberController {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
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

//	// 로그인 처리
//	@RequestMapping(value = "/login.do", method = { RequestMethod.GET, RequestMethod.POST })
//	public String doLogin(Member member, HttpSession session) {
//		System.out.println("아이디: " + member.getId() + " 비밀번호: " + member.getPassword());
//		Member getMemberInfo = memberService.findByIdAndPassword(member.getId(), member.getPassword());
//		System.out.println("getMemberInfo: " + getMemberInfo);
//
//		if (getMemberInfo == null) {
//			session.setAttribute("member", null);
//			return "member/login";
//		} else {
//			session.setAttribute("member", getMemberInfo);
//
//			session.setAttribute("nickname", getMemberInfo.getNickname());
//			session.setAttribute("id", getMemberInfo.getId());
//
//			System.out.println("로그인 성공" + getMemberInfo);
//			return "redirect:/";
//		}
//	}

//	// 로그아웃 처리
//	@RequestMapping("/logout.do")
//	public String doLogout(Member member, @AuthenticationPrincipal SecurityUser securityUser) {
//		
//		session.invalidate();
//		return "redirect:/login";
//	}


	// 회원가입 화면
	@RequestMapping("/join")
	public String join() {
		return "member/join";
	}
	// 회원가입 화면
		@RequestMapping("/styleSelect1")
		public String styleSelect1() {
			return "member/styleSelect1";
		}
	// 회원가입 처리
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String doJoin(Member member, Model model, HttpSession session) {
		System.out.println("아이디: " + member.getId() + " 비밀번호: " + member.getPassword());
		model.addAttribute("id",member.getId());
		model.addAttribute("password",encoder.encode(member.getPassword()));
		model.addAttribute("nickname",member.getNickname());
		model.addAttribute("phonenumber",member.getPhonenumber());
		model.addAttribute("email",member.getEmail());
		System.out.println("Model1::" + model);
		return "member/styleSelect3";
	}
	// 회원가입 스타일 처리1
	@RequestMapping(value = "/styleSelect1.do", method = RequestMethod.POST)
	public String doStyleSelect1(Member member, Model model, HttpSession session) {
		return "member/styleSelect3";
		
	
	}
	
	// 회원가입 스타일 처리2

	// 회원가입 스타일 처리3
		@RequestMapping(value = "/styleSelect3.do", method = RequestMethod.POST)
		public String doStyleSelect3(Member member, Model model, HttpSession session) {
			System.out.println("Model2::"+model);
			model.addAttribute(member);
			System.out.println("Model3::"+model);
			System.out.println("Member2::"+member);
			Style getStyleInfo = member.getStyleNo1();
			if (getStyleInfo.getStyleNo() == 0) {
				session.setAttribute("style", null);
				return "member/styleSelect3";
			} else {
				memberService.doJoin(member);
				System.out.println("회원가입 성공" + getStyleInfo);
				return "redirect:/login";
			}
		}

		
	// 정보 변경 화면
	@RequestMapping("user/checkPassword")
	public String checkPassword() {
		return "member/checkPassword";
	}
	//정보 변경 전 비밀번호 확인
	@RequestMapping(value = "user/checkPassword.do", method = RequestMethod.POST)
	public String doCheckPassword(Member member, HttpSession session) {
		Member getMemberInfo = memberService.findByPassword(member.getPassword());
		System.out.println("getMemberInfo: " + getMemberInfo);
		if (getMemberInfo == null) {
			session.setAttribute("member", null);
			System.out.println("비밀번호 확인 실패");
			return "member/checkPassword";
		} else {
			session.setAttribute("member", getMemberInfo);
			session.setAttribute("password", getMemberInfo.getPassword());
			System.out.println("비밀번호 확인 성공" + getMemberInfo);
			return "redirect:/member/modProfileAll";
		}
	}
	// 정보 변경 화면
		@RequestMapping("user/modProfileAll")
		public String modProfileAll() {
			return "member/modProfileAll";
		}
	// 정보 변경 처리
	@RequestMapping(value = "user/modProfileAll.do", method = RequestMethod.POST)
	public String doModProfileAll(Member member, Model model, HttpSession session) {
		Member getMemberInfo = memberService.findByPassword(member.getPassword());
		System.out.println("getMemberInfo: " + getMemberInfo);
//		model.addAttribute("password",encoder.encode(member.getPassword()));
//		model.addAttribute("nickname",member.getNickname());
//		model.addAttribute("phonenumber",member.getPhonenumber());
//		model.addAttribute("email",member.getEmail());
		model.addAttribute(member);
		return "member/profile";
	}
	

	// 마이프로필 화면
	@RequestMapping("user/profile")
	public String profileSetting() {
		return "member/profile";
	}
	
	//프로필 화면
	@RequestMapping("user/modProfile")
	public String modProfile() {
		return "member/modProfile";
	}	
	// 프로필 변경
		@RequestMapping(value = "user/modProfile.do", method = RequestMethod.POST)
		public String doModProfile(Member member, HttpSession session) {
			return "redirect:/profile";
		}
		
		
	//이미지 선택형 스타일 선택 
		@RequestMapping(value="/styleSelectByImg", method=RequestMethod.GET)
		public String doStyleSelctByImg() {
			
			return "/member/styleSelectByImage";
		}
	//파일 가져오기
		@ResponseBody
		@RequestMapping("/getFileList")
		private ModelAndView getStyleList(HttpServletRequest request) throws Exception{
			ModelAndView modelAndView = new ModelAndView("jsonView");
			List<File> sampleImgList = fileRepository.findByTypeContaining("sample");
			modelAndView.addObject("sampleImgList", sampleImgList);
			
			return modelAndView;
		}
		
		
}

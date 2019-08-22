package com.fashionlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.service.MemberService;

@SessionAttributes("member")
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;

	@RequestMapping(value="/")
	public String main(){
		return "main";
	}

	// 로그인
	@RequestMapping("/login")
	public String login(Model model, String error, String logout,HttpServletRequest httpServletRequest) {
		if(logout != null) {
			model.addAttribute("logout", "로그아웃 성공");
			return "login";	
		}else {
			System.out.println("로그인 실패");
			return "login";	
		}
	}
	
	//로그인 실패
	@RequestMapping(value="/loginError")
	public String loginError(Model model, String id) {
		model.addAttribute("error", "오류");
		model.addAttribute("id", id);
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String doLogin(Member member, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		Member getMemberInfo = memberService.getMemberInfo(member);

		if (getMemberInfo == null) {
			session.setAttribute("member", null);
			System.out.println("로그인성공");
			return "login";
		} else {
			session.setAttribute("member", getMemberInfo);
			System.out.println("로그인 실패");
			return "redirect:/";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/join")
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/Join.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String signupProcess(Member member) {
		System.out.println("Member::" + member);
		memberService.doJoin(member);
		return "redirect:login";
	}

}

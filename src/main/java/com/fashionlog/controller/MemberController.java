package com.fashionlog.controller;


import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.service.MemberService;


@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	//로그인
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest httpServletRequest) {
		
		return "login";
	}
	@GetMapping("/join")
	public void joinview() {
	}
	@PostMapping("/join")
	public String join() {
		return "";
	}
	
	
	
	

}

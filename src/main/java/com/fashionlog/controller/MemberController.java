package com.fashionlog.controller;

import java.util.List;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.service.MemberService;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;

@RestController
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
}

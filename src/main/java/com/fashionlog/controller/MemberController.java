package com.fashionlog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
	@GetMapping("/login")
	public void loginview() {
	}
	
	@PostMapping("/login")
	public String login() {
		return "";
	}
	@GetMapping("/join")
	public void joinview() {
	}
	@PostMapping("/join")
	public String join() {
		return "";
	}
	
	
	
	

}

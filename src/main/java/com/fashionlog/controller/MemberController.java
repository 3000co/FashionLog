package com.fashionlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프");
	}

	@GetMapping("/login")
	public void login() {

	}

}

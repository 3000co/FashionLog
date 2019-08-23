package com.fashionlog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;

@RestController
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	
	@RequestMapping("/member/{id}")
	@ResponseBody
	public Member getMember(@PathVariable String id) {
		Member mem = new Member();
		mem.setId(id);
		Member memresult = memberRepository.findById(id);
		
		return memresult;
	}
}


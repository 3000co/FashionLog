package com.fashionlog.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.service.LikesService;
import com.fashionlog.security.SecurityUser;

@RestController
public class LikesController {
	@Autowired
	private LikesService likesService;
	@Autowired
	private LikesRepository likesRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PostRepository postRepository;

	// 내가 좋아요 했는지 안했는지 체크
	@RequestMapping("/checkLikes")
	public String checkLikes(Integer postNo, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		user = memberRepository.findById(user.getId());
		Post post = postRepository.findById(postNo).get();
		return likesRepository.findByMemberNoAndPostNo(user, post).isPresent() ? "likes" : "unLikes";
	}

	// 좋아요 하기
	@RequestMapping("/doLikes")
	public String doLikes(Integer postNo, @AuthenticationPrincipal SecurityUser securityUser) throws Exception {
		Member user = securityUser.getMember();
		user = memberRepository.findById(user.getId());
		Post post = postRepository.findById(postNo).get();
		if (likesRepository.findByMemberNoAndPostNo(user, post).isPresent()) {
			throw new Exception("Already liked");
		} else {
			return likesService.doLike(user, post) + "";
		}
	}

	// 좋아요 취소
	@RequestMapping("/unLikes")
	public String unLikes(Integer postNo, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		user = memberRepository.findById(user.getId());
		Post post;
		try {
			post = postRepository.findById(postNo).get();
		} catch (NoSuchElementException e) {
			return "postNotFound";
		}
		try {
			return likesService.unLike(user, post) + "";
		} catch (NoSuchElementException e) {
			return "likesNotFound";
		}
	}
}

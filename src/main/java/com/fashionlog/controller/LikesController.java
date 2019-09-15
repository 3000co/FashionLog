package com.fashionlog.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.service.LikesService;

@Controller
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
	@ResponseBody
	public String checkLikes(Integer postNo, HttpSession session) {
		Member user = (Member) session.getAttribute("member");
		user = memberRepository.findById(user.getId());
		Post post = postRepository.findById(postNo).get();
		return likesRepository.findByMemberNoAndPostNo(user, post).isPresent() ? "likes" : "unLikes";
	}

	// 좋아요 하기
	@RequestMapping("/doLikes")
	@ResponseBody
	public String doLikes(Integer postNo, HttpSession session) {
		Member user = (Member) session.getAttribute("member");
		user = memberRepository.findById(user.getId());
		Post post = postRepository.findById(postNo).get();
		likesService.doLike(user, post);
		return post.getPostNo() + "";
	}

	// 좋아요 취소
	@RequestMapping("/unLikes")
	@ResponseBody
	public String unLikes(Integer postNo, HttpSession session) {
		Member user = (Member) session.getAttribute("member");
		user = memberRepository.findById(user.getId());
		Post post;
		try {
			post = postRepository.findById(postNo).get();
		} catch (NoSuchElementException e) {
			return "postNotFound";
		}
		try {
			likesService.unLike(user, post);
			return post.getPostNo() + "";
		} catch (NoSuchElementException e) {
			return "likesNotFound";
		}
	}
}

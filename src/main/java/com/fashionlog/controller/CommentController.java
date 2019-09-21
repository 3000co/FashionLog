package com.fashionlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.security.SecurityUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PostRepository postRepository;


	/*
	 * @RequestMapping("/comment")
	 * 
	 * @ResponseBody public List<Comment> getCommentList(Model model, Post postNo) {
	 * List<Comment> commentList = commentRepository.findByPostNo(postNo);
	 * model.addAttribute("commentList", commentList); return commentList; }
	 */

	@RequestMapping("/insertComment")
	@ResponseBody
	public void insertComment(@ModelAttribute Comment comment, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = memberRepository.findById(securityUser.getMember().getId());
		System.out.println(user);
		comment.setMemberNo(user);
		commentRepository.save(comment);
	}

	@RequestMapping("/deleteComment/{commentNo}")
	@ResponseBody
	public String deleteComment(@PathVariable int commentNo) {
		commentRepository.deleteById(commentNo);
		return "redirect:/comment";
	}

}

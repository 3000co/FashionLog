package com.fashionlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


	@RequestMapping("/comment")
	@ResponseBody
	public List<Comment> getCommentList(Model model, Post postNo) {
		List<Comment> commentList = commentRepository.findByPostNo(postNo);
		model.addAttribute("commentList", commentList);
		return commentList;
	}

	@RequestMapping("/insertComment")
	@ResponseBody
	public String insertComment(Comment comment, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember(); 
		comment.setMemberNo(user);
		commentRepository.save(comment);
		return "abab";
	}

	@RequestMapping("/deleteComment")
	@ResponseBody
	public String deleteComment(int commentNo, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		Comment comm = commentRepository.findById(commentNo).get();
		if (comm.getMemberNo().getId() == user.getId()){
			commentRepository.deleteById(commentNo);
			return commentNo+" is deleted";
		}
		return "delete denied";
	}

}

package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PostRepository postRepository;
	

	//	@RequestMapping("/")
	//	public String moveView() {
	//		return "view";
	//	}

	@RequestMapping("/comment")
	public String getCommentList(Model model) {

		List<Object[]> commentList = commentRepository.getCommentList();
		for(Object[] item : commentList) {
			System.out.println(Arrays.toString(item));

		}
		model.addAttribute("commentList", commentList);
		return "view";	
	}


	@RequestMapping("/insertComment")
	public String insertComment(HttpServletRequest request) {
		Comment comment = new Comment();
		Member member = memberRepository.findById(3).get();
		Post post = postRepository.findById(9).get();
		
		//postNo은 샘플 데이터를 넣어둠. 세션에서 받을 예정. 
		comment.setMember(member);
		comment.setContents(request.getParameter("contents"));
		comment.setPost(post);
		commentRepository.save(comment);

		return "redirect:/";
	}

	@RequestMapping("/deleteComment")
	public String deleteComment(@RequestParam("commentNo")int commentNo) {
		commentRepository.deleteById(commentNo);
		return "redirect:/";
	}


}

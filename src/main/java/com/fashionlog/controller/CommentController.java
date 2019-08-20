package com.fashionlog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dto.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {
	@Autowired
	CommentRepository commentRepository;
	
//	@RequestMapping("/")
//	public String moveView() {
//		return "view";
//	}
	
	@RequestMapping("/")
	public String getCommentList(Model model, Comment comment) {
		
	List<Comment> commentList = commentRepository.findAll();
	model.addAttribute("commentList", commentList);
	
		return "view";	
	}
	
	@RequestMapping("/insertComment")
	public String insertComment(HttpServletRequest request) {
		Comment comment = new Comment();
		//memberNo과 postNo은 샘플 데이터를 넣어둠. 세션에서 받을 예정. 
		comment.setMemberNo(3);
		comment.setContents(request.getParameter("contents"));
		comment.setPostNo(9);
		comment.setUploadTime(new Date());
		commentRepository.save(comment);
		
		return "redirect:/";
	}
	
	@RequestMapping("/deleteComment")
	public String deleteComment(@RequestParam("commentNo")int commentNo) {
		commentRepository.deleteById(commentNo);
		return "redirect:/";
	}
	

}

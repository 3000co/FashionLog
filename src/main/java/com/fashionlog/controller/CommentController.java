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
	

	//	@RequestMapping("/")
	//	public String moveView() {
	//		return "view";
	//	}

	@RequestMapping("/comment")
	@ResponseBody
	public List<Comment> getCommentList(Model model, Post postNo) {
//		public String getCommentList(Model model, int postNo) {

		List<Comment> commentList = commentRepository.findByPostNo(postNo);
//		List<Comment> commentList = commentRepository.findAll();
		model.addAttribute("commentList", commentList);
		return commentList;
	}
	
//	public String getCommentList(Model model) {
//		
//		List<Object[]> commentList = commentRepository.getCommentList();
//		for(Object[] item : commentList) {
//			System.out.println(Arrays.toString(item));
//		}
//		model.addAttribute("commentList", commentList);
//		return "view";
//	}


	@RequestMapping("/insertComment")
	public String insertComment(Comment comment, @AuthenticationPrincipal SecurityUser securityUser) {
//		Comment comment = new Comment();
//		Member member = memberRepository.findById(session.getAttribute("id")+"");
		Member user = securityUser.getMember();
//		Post post = postRepository.findById(postNo).get();
		
		//postNo은 샘플 데이터를 넣어둠. 세션에서 받을 예정. 
//		comment.setMemberNo(user);
//		comment.setContents(request.getParameter("contents"));
//		comment.setPostNo(post);
		commentRepository.save(comment);

		return "redirect:/comment";
	}

	@RequestMapping("/deleteComment")
	public String deleteComment(@RequestParam("commentNo")int commentNo) {
		commentRepository.deleteById(commentNo);
		return "redirect:/comment";
	}


}

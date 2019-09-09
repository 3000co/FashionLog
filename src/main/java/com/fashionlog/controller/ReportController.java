package com.fashionlog.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dao.ReportRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.dto.Report;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReportController {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	MemberRepository memberRepository;
	

	//다음 기능은 관리자만 사용 가능합니다.
	
	@RequestMapping("/admin/report")
	public String showReportList(Model model) {

		List<Report> reportedThingsList = reportRepository.findAll();
		
		model.addAttribute("reportedThingsList", reportedThingsList);
		
		//실제로는 관리자 페이지의 신고 게시물 관리로 들어가야 함.
		return "adminOnly/adminReport";	
	}


	@RequestMapping("/insertReport")
	@ResponseBody
	public String insertReport(@ModelAttribute("Report") Report report,HttpServletRequest request, HttpSession session) {

		//ReportMemNo
		String reportMemID = (String)session.getAttribute("id");
		Member member = memberRepository.findById(reportMemID);
		report.setReportMemNo(member);
		
		//targetMemNo
		if(report.getTargetPostNo() != null) {
			int targetPostNo = report.getTargetPostNo().getPostNo();
			Post targetPost = postRepository.findById(targetPostNo).get();
			report.setTargetMemNo(targetPost.getMemberNo());
		}else if(report.getTargetCommentNo() != null) {
			int targetCommentNo = report.getTargetCommentNo().getCommentNo();
			Comment targetComment = commentRepository.findById(targetCommentNo).get();
			report.setTargetMemNo(targetComment.getMemberNo());
		}
		//System.err.println("???????????????????????");
		//System.err.println(report.toString());
		reportRepository.save(report);

		return "redirect:/comment";
	}

	@Transactional
	@RequestMapping("/deleteReportedThing")
	public String deleteReportedThing(@RequestParam("reportNo")int reportNo) {
		Report report = reportRepository.findById(reportNo).get();
		
		if(report.getTargetPostNo() != null) {
			int targetPostNo = report.getTargetPostNo().getPostNo();
			postRepository.deleteById(targetPostNo);
			changeCheckHistory(reportNo, "삭제");
			
		}else if(report.getTargetCommentNo() != null) {
			int targetCommentNo = report.getTargetCommentNo().getCommentNo();
			System.err.println("댓글 삭제 처리: "+targetCommentNo);
			commentRepository.deleteById(targetCommentNo);
			changeCheckHistory(reportNo, "삭제");
		}
		return "redirect:/adminReport";
	}
	
	@RequestMapping("/lgnoreReport")
	public String ignoreReport(@RequestParam("reportNo")int reportNo) {
		changeCheckHistory(reportNo, "무시");
		return "redirect:/admin/report";
	}
	
	public Report changeCheckHistory(int reportNo,String contents) {
		Report report = reportRepository.findById(reportNo).get();
		report.setCheckHistory(contents);
		
		return reportRepository.save(report);
	}


}

package com.fashionlog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;

@RestController
public class NotificationController {
	@Autowired
	private NotificationRepository notificiationRepository;
	
	@RequestMapping("/noti/{memberNo}")
	@ResponseBody
	public List<Notification> notificationList(@PathVariable int memberNo, Model model) {
		Member reciever = new Member();
		reciever.setMemberNo(memberNo);
		
		List<Notification> uncheckedNoti = notificiationRepository.findByRecieverMemNo(reciever);
		System.out.println(uncheckedNoti);
		model.addAllAttributes(uncheckedNoti);
	
//		return "notification/notification";
		return uncheckedNoti;
	}
	
	@RequestMapping("/noti/all")
	@ResponseBody
	public List<Notification> notificationAll() {
		
		List<Notification> AllNotiResult = notificiationRepository.findAll();
		
		return AllNotiResult;
	}
	
	
	
	@RequestMapping("/notiSender/{recieverNo}")
	@ResponseBody
	public List<Notification> trackSender(@PathVariable int recieverNo) {
		Member reciever = new Member();
		reciever.setMemberNo(recieverNo);
		
		return notificiationRepository.findSewersdfarqernderMemNoByRecieverMemNo(reciever);
	}
	
	@RequestMapping("/getNoti/{notificationNo}")
	@ResponseBody
	public Notification getNoti(@PathVariable int notificationNo) {
		Optional<Notification> result = notificiationRepository.findById(notificationNo);
		
		return result.get();
	}

	
//	@RequestMapping("/notiSender/{recieverNo}")
//	@ResponseBody
//	public List<Integer> trackSenderBy(@PathVariable int commentNo) {
//		Comment cmt = new Comment();
//		cmt.setCommentNo(commentNo);
//		
//		return notificiationRepository.findSenderMemNoByRecieverMemNo(cmt);
//	}
	
	
}

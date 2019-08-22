package com.fashionlog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.NotificiationRepository;
import com.fashionlog.model.dto.Notification;

@Controller
public class NotificationController {
	private NotificiationRepository notificiationRepository;
	
	@RequestMapping("/noti/{memberNo}")
	public String notificationList(@PathVariable int memberNo, Model model) {
		
		List<Notification> uncheckedNoti = notificiationRepository.findByRecieverMemNo(memberNo);
		model.addAllAttributes(uncheckedNoti);
	
		return "notification/notification";
	}
	
	@RequestMapping("/noti/all")
	@ResponseBody
	public List<Notification> notificationAll() {
		
		List<Notification> AllNotiResult = notificiationRepository.findAll();
		return AllNotiResult;
		
	}
	
	
}

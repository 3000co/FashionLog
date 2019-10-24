package com.fashionlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;
import com.fashionlog.model.service.NotificationService;
import com.fashionlog.security.SecurityUser;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Controller
public class NotificationController {
	@Autowired
	private NotificationRepository notificiationRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/noti/all")
	public String notificationAll(Model model) {
		List<Notification> allNotiList = notificiationRepository.findAll();
		model.addAttribute("allNotiList",allNotiList);
		return "/notification/NotiListAll";
	}
	
	//확인되지 않은 알림 불러오기
	@RequestMapping("/noti/unchecked")
	@ResponseBody
	public List<Notification> uncheckedNotificationList(@AuthenticationPrincipal SecurityUser securityUser) {
		Member user = memberRepository.findById(securityUser.getMember().getId());
		List<Notification> notiList = notificiationRepository.findByRecieverMemNoAndCheckTimeIsNull(user);
		return notiList;
	}

	//확인되지 않은 알림 불러오기
	@RequestMapping("/noti")
	public String notiTest(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = memberRepository.findById(securityUser.getMember().getId());
		List<Notification> notiList = notificiationRepository.findByRecieverMemNoAndCheckTimeIsNull(user);
		model.addAttribute("notiList", notiList);
		return "notification/notification";
	}
	
	//알림을 확인
	@RequestMapping(value = "noti/check", method = RequestMethod.POST)
	public String checkNotification(Notification noti) {
		Notification requestedNotification = notificiationRepository.findById(noti.getNotiNo()).get();
		notificationService.checkNotification(requestedNotification);
		System.err.println( "redirect:/"+notificationService.moveToEvent(requestedNotification));
		return "redirect:/"+notificationService.moveToEvent(requestedNotification);
	}
	
}

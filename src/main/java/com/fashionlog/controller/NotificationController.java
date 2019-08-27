package com.fashionlog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;
import com.fashionlog.model.service.NotificationService;

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
		System.out.println(allNotiList);
		return "/notification/NotiListAll";
	}
	
	//확인되지 않은 알림 불러오기
	@RequestMapping("/noti/{memberNo}/unchecked")
	public String uncheckedNotificationList(@PathVariable int memberNo, Model model, HttpSession session) {
		Member reciever = memberRepository.findById(memberNo).get();
		session.setAttribute("member", reciever);
		List<Notification> notiList = notificiationRepository.findByRecieverAndCheckTimeIsNull(reciever);
		System.out.println(notiList);
		model.addAttribute("notiList",notiList);
		return "/notification/Notification";
	}
	
	//알림을 확인
	@RequestMapping(value = "noti/check", method = RequestMethod.POST)
	public String checkNotification(Notification noti) {
		Notification requestedNotification = notificiationRepository.findById(noti.getNotiNo()).get();
		notificationService.checkNotification(requestedNotification);
		return notificationService.moveToEvent(requestedNotification);
	}
	
}

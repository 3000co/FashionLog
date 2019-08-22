package com.fashionlog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Notification;
import com.fashionlog.model.dto.SocialEvent;

import javassist.expr.Instanceof;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public void enrollNotification(SocialEvent socialEvent) {
		Notification eventNoti = new Notification();
		if(socialEvent instanceof Comment) {
			eventNoti.setType(1);
			
		} else if(socialEvent instanceof Likes){
			
		} else if(socialEvent instanceof Follow){
			
		}
	}

	@Override
	public void deleteNotification(SocialEvent socialEvent) {
		// TODO Auto-generated method stub

	}

}

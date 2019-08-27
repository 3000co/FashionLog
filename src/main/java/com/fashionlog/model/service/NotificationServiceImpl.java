package com.fashionlog.model.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.NotificationRepository;
import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Likes;
import com.fashionlog.model.dto.Notification;
import com.fashionlog.model.dto.SocialEvent;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	
	@Override
	public void enrollNotification(SocialEvent event) {
		Notification eventNoti = new Notification();
		eventNoti.setType(assortEvent(event));
		switch (eventNoti.getType()) {
		case EventType.LIKES:
			Likes likesEvent = (Likes) event;
			eventNoti.setLikes(likesEvent);
			eventNoti.setReciever(likesEvent.getPost().getMember());
			eventNoti.setSender(likesEvent.getMember());
			break;
		case EventType.COMMENT:
			Comment commentEvent = (Comment) event;
			eventNoti.setComment(commentEvent);
			eventNoti.setReciever(commentEvent.getPost().getMember());
			eventNoti.setSender(commentEvent.getMember());
			break;
		default:
			Follow followEvent = (Follow) event;
			eventNoti.setFollow(followEvent);
			eventNoti.setReciever(followEvent.getFollowee());
			eventNoti.setSender(followEvent.getFollower());
			break;
		}
		notificationRepository.save(eventNoti);
	}

	@Override
	public void checkNotification(Notification noti) {
		Timestamp checkedTime = new Timestamp(System.currentTimeMillis());
		noti.setCheckTime(checkedTime);
		notificationRepository.save(noti);
	}
	
	@Override
	public String moveToEvent(Notification noti) {
		switch(noti.getType()) {
		case EventType.LIKES: 
			return "post/" + noti.getLikes().getPost();
		case EventType.COMMENT: 
			return "post/" + noti.getComment().getPost(); 
		default:
			return "profile/" + noti.getFollow().getFollower().getMemberNo();
		}
	}
	
	private int assortEvent(SocialEvent event) {
		int type;
		if(event instanceof Likes) {
			type = EventType.LIKES; 
		} else if(event instanceof Comment) {
			type = EventType.COMMENT;
		} else {
			type = EventType.FOLLOW;
		}
		return type;
	}
	
	
	// 알림을 보내는 이벤트 타입
	static class EventType {
		final static int LIKES = 1; 
		final static int COMMENT = 2; 
		final static int FOLLOW = 3; 
	}
}

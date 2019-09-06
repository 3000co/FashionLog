package com.fashionlog.model.service;

import com.fashionlog.model.dto.Notification;
import com.fashionlog.model.dto.SocialEvent;

public interface NotificationService {

	public void enrollNotification(SocialEvent event);

	public void checkNotification(Notification noti);
	
	public String moveToEvent(Notification noti);
	
}
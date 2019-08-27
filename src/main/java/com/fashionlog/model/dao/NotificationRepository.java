package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	public List<Notification> findByRecieverAndCheckTimeIsNull(Member reciever);
	
	public List<Notification> findByReciever(Member reciever);
	
	public List<Notification> findSenderByReciever(Member reciver);
	
}

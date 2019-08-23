package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	public List<Notification> findByRecieverMemNoAndCheckTimeIsNull(int recieverMemNo);
	
	public List<Notification> findByRecieverMemNo(Member recieverMem);
	
	public List<Notification> findSewersdfarqernderMemNoByRecieverMemNo(Member reciverMem);
	
}

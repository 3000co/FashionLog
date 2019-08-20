package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Notification;

public interface NotificiationRepository extends JpaRepository<Notification, Integer> {

	public List<Notification> findByRecieverMemNoAndCheckTimeIsNull(int RecieverMemNo);
	
}

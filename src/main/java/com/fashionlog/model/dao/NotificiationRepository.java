package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Notification;

public interface NotificiationRepository extends CrudRepository<Notification, Integer> {

	public List<Notification> findByRecieverMemNoAndCheckTimeIsNull(int RecieverMemNo);
	
}

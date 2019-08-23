package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Likes implements SocialEvent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likesNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member MemberNo;
	
	@ManyToOne
	@JoinColumn(name = "POST_NO")
	private Post postNo;
	
	@CreationTimestamp
	Timestamp likesTime;
	
	@OneToOne(mappedBy = "notificationNo", cascade = CascadeType.REMOVE)
	Notification notification;
	
}

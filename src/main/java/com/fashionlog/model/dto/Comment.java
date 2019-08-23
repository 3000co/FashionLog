package com.fashionlog.model.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.management.Notification;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member memberNo;
	
	@ManyToOne
	@JoinColumn(name = "POST_NO")
	private Post postNo;
	
	@CreationTimestamp
	Timestamp uploadTime;
	
	String contents;
	
//	@OneToOne(mappedBy = "notificationNo", cascade = CascadeType.REMOVE)
//	Notification notification;
	
	
		
	}
	



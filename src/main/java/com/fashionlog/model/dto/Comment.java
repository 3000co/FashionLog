package com.fashionlog.model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString(exclude = "notification")
public class Comment implements SocialEvent{


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

	
	@OneToOne(mappedBy = "commentNo", cascade = CascadeType.REMOVE)
	Notification notification;



	
}
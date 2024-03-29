package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "notification")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class Likes implements SocialEvent{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likesNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member memberNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_NO")
	private Post postNo;
	
	@CreationTimestamp
	Timestamp likesTime;
	
	@JsonIgnore
	@OneToOne(mappedBy = "likesNo", cascade = CascadeType.REMOVE)
	Notification notification;
	
}

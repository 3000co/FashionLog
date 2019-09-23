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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "notification")
@Entity
public class Follow implements SocialEvent{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followNo;
	
	@ManyToOne
	@JoinColumn(name = "followerMemNo")
	private Member followerMemNo;
	
	@ManyToOne
	@JoinColumn(name = "followeeMemNo")
	private Member followeeMemNo;
	
	@CreationTimestamp
	Timestamp followTime;
	
	@JsonIgnore
	@OneToOne(mappedBy = "followNo", cascade = CascadeType.REMOVE)
	Notification notification;

}

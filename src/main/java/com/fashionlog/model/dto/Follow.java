package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
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
	
}
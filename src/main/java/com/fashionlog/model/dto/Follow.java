package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Follow {

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

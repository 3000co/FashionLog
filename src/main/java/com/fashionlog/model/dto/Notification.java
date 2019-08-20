package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int notiNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberNo")
	int senderMemNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberNo")
	int recieverMemNo;
	
	int type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "likesNo")
	int likesNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "commentNo")
	int commentNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "followNo")
	int followNo;
	
	@Column
	Timestamp sendTime;
	
	@Column
	Timestamp checkTime;
	
}

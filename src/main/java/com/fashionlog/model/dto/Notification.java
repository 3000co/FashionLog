package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notiNo;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SENDER_MEM_NO")
	private Member senderMemNo;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECIEVER_MEM_NO")
	private Member recieverMemNo;
	
	private int type;
	
	@OneToOne
	@JoinColumn(name = "LIKES_NO")
	private Likes likesNo;
	
	@OneToOne
	@JoinColumn(name = "COMMENT_NO")
	private Comment commentNo;
	
	@OneToOne
	
	@JoinColumn(name = "FOLLOW_NO")
	private Follow followNo;
	
	@CreationTimestamp
	private Timestamp sendTime;

	private Timestamp checkTime;
	
}

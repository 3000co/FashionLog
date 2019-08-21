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
public class Likes {
	
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

}

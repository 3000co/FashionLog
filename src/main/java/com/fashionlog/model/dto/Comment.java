package com.fashionlog.model.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;
	private int memberNo;
	private int postNo;
	private Date uploadTime;
	private String contents;
	
//	@OneToOne(optional=true)
//	@JoinColumn(name="memberNo")
//	private Member member;
}

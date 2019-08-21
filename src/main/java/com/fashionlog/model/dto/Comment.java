package com.fashionlog.model.dto;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name="memberNo")
	private Optional<Member> member;
	private int postNo;
	private Date uploadTime;
	private String contents;
	
	
		
	}
	



package com.fashionlog.model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
	@CreationTimestamp
	private Timestamp uploadTime;
	
	@OneToOne
	@JoinColumn(name = "POST_IMAGE_NO")
	private File postImage;
	
	private String contents;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO1")
	private Style style1;

	@ManyToOne
	@JoinColumn(name = "STYLE_NO2")
	private Style style2;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO3")
	private Style style3;
	
	
	@OneToMany(mappedBy = "commentNo")
	private List<Comment> commentList = new ArrayList<Comment>();
}

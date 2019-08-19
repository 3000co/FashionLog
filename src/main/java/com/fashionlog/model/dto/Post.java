package com.fashionlog.model.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Post {
	@Id
	@GeneratedValue
	private int postNo;
	private int memberNo;
	private Date uploadTime;
	private int postImageNo;
	private String contents;
	private int styleNo1;
	private int styleNo2;
	private int styleNo3;
	
	
	
	
}
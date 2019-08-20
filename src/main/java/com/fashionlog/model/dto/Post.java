package com.fashionlog.model.dto;

import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "post")
public class Post {
	@Id
	private int postNo;
	private int memberNo;
	private Date uploadTime;
	private int postImageNo;
	private String contents;
	private int styleNo1;
	private int styleNo2;
	private int styleNo3;
}

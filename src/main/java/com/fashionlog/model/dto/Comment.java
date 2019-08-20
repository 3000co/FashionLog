package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int commentNo;
	
	@ManyToOne(targetEntity = Member.class)
	@JoinTable(name = "MEMBER",
	joinColumns = @JoinColumn(name = "MEMBER_NO"))
	int memberNo;
	
	@ManyToOne
	@JoinColumn(name = "postNo")
	int postNo;
	
	@CreationTimestamp
	Timestamp uploadTime;
	
	String contents;
	
	
	
}

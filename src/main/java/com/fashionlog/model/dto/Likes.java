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
	int likesNo;
	
	@ManyToOne
	@JoinTable(name = "MEMBER")
	int MemberNo;
	
	@ManyToOne
	@JoinColumn(name = "postNo", nullable = false)
	int postNo;
	
	@CreationTimestamp
	Timestamp followTime;

}

package com.fashionlog.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int memberNo;
	
	String id;
	
	String password;
	
	String nickname;
	
	String phonenumber;
	
	String email;
	
	@OneToOne
	@JoinColumn(name = "fileNo")
	int profileImageNo;
	
	@ManyToOne
	@JoinColumn(name = "styleNo", nullable = false)
	int styleNo1;
	
	@ManyToOne
	@JoinColumn(name = "styleNo")
	int styleNo2;
	
	@ManyToOne
	@JoinColumn(name = "styleNo")
	int styleNo3;
	
	
}

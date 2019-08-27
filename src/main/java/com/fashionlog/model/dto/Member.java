package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {
	@Id
	private int memberNo;
	
	@Column(columnDefinition = "char")
	private String id;
	 
	@Column(columnDefinition = "char")
	private String password;
	
	@Column(columnDefinition = "char")
	private String nickname;
	
	@Column(columnDefinition = "char")
	private String phonenumber;
	
	@Column(columnDefinition = "char")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "PROFILE_IMAGE_NO")
	private File profileImage;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO1")
	private Style style1;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO2")
	private Style style2;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO3")
	private Style style3;

}


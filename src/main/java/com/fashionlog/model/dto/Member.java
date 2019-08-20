package com.fashionlog.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member{
	@Id
	private int memberNo;
	private String id;
	private String password;
	private String nickname;
	private String phonenumber;
	private String email;
	private int profileImageNo;
	private int styleNo1;
	private int styleNo2;
	
}

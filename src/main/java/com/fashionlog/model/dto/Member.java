package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Column( name="id", columnDefinition ="char")  
	private String id;
	
	@Column( name="password", columnDefinition ="char")  
	private String password;
	
	@Column( name="nickname", columnDefinition ="char")  
	private String nickname;
	
	@Column( name="phonenumber", columnDefinition ="char")  
	private String phonenumber;
	
	@Column( name="email", columnDefinition ="char")  
	private String email;
	
	private int profileImageNo;
	private int styleNo1;
	private Integer styleNo2;
	private Integer styleNo3;
}

package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberNo;
	@Column( name="id", columnDefinition ="char")  
	private char id;
	@Column( name="password", columnDefinition ="char")  
	private char password;
	@Column( name="nickname", columnDefinition ="char")  
	private char nickname;
	@Column( name="phonenumber", columnDefinition ="char")  
	private char phonenumber;
	@Column( name="email", columnDefinition ="char")  
	private char email;
	@ManyToOne 
	@JoinColumn(name = "profileImageNo", referencedColumnName = "fileNo")
	private File file;
	private int styleNo1;
	private int styleNo2;

}

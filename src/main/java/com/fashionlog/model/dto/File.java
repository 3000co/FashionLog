package com.fashionlog.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@ToString
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileNo;
	@Column( name="type", columnDefinition ="char")  
	private char type;
	@Column( name="name", columnDefinition ="char")
	private char name;
	private String path;
	private int size;
	@Column( name="state", columnDefinition ="char")
	private char state;
	private Date uploadTime;
	private Date deleteTime;
	
	
	
	

}

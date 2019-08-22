package com.fashionlog.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter@Setter@ToString
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileNo;
	@Column( name="type", columnDefinition ="char")  
	private String type;
//	@Column( name="name", columnDefinition ="char")
//	private char name;
	private String name;
	private String path;
	private int size;
	@Column(columnDefinition ="char default 'published'")
	private String state;
	private Date uploadTime;
	@Column(nullable = true)
	private Date deleteTime;
	
	
	
	

}

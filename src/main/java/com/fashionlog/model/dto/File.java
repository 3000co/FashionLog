package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int fileNo;
	
	@Column(columnDefinition = "char")
	String type;
	
	@Column(columnDefinition = "char")
	String name;
	
	String path;
	
	int size;
	
	@Column(columnDefinition = "char")
	String state;
	
	@CreationTimestamp
	Timestamp uploadTime;
	
	@UpdateTimestamp
	Timestamp deleteTime;
	
}

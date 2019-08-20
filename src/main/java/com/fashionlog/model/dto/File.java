package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	String type;
	
	String name;
	
	String path;
	
	int size;
	
	String state;
	
	Timestamp uploadTime;
	
	Timestamp deletedTime;
	
}

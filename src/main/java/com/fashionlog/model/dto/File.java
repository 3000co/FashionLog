package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter@Setter@ToString
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class File {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int fileNo;
	
	@Column(columnDefinition = "char")
	String type;

	@Column(name="name", columnDefinition = "char")
	String name;
	
	String path;
	
	int size;
	
	@Column(columnDefinition = "char")
	String state;
	
	@CreationTimestamp
	Timestamp uploadTime;
	
	Timestamp deleteTime;
	
}

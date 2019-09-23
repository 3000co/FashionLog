package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_NO")
	private Post postNo;

	private int tagNo;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_NO")
	private Category categoryNo;
	
	@ManyToOne
	@JoinColumn(name = "BRAND_NO")
	private Brand brandNo;
	
	@Column(columnDefinition = "char")
	private String color;
	
	private String store;
	
	private float xCoordinate;
	
	private float yCoordinate;

}

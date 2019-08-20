package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {
	@Id
//	@GeneratedValue
	private int itemNo;
	
//	@ManyToOne(targetEntity = Post.class)
//	@JoinColumn(name = "POST_NO")
	private int postNo;
	
	private int tagNo;
	
	private String name;
	
//	@OneToOne
//	@JoinColumn(name = "CATECORY_NO")
	private int categoryNo;
	
//	@OneToOne
//	@JoinColumn(name = "BRAND_NO")
	private int brandNo;
	
	@Column( name="color", columnDefinition ="char")
	private String color;
	
	private String store;
	
	private float xCoordinate;
	
	private float yCoordinate;
}

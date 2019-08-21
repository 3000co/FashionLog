package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemNo;
	@ManyToOne
	@JoinColumn(name = "POST_NO")
	private Post postNo;
	private int tagNo;
	private String name;
	@ManyToOne
	@JoinColumn(name = "CATEGORY_NO")
	private Category categoryNo;
	@ManyToOne
	@JoinColumn(name = "BRAND_NO")
	private Brand brandNo;
	@Column(name="color", columnDefinition ="char")
	private String color;
	private String store;
	private float xCoordinate;
	private float yCoordinate;
}

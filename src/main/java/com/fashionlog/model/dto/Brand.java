package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "brand")
public class Brand implements Comparable<Brand> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brandNo;
	@Column(columnDefinition ="char")
	private String name;
	
	@Column(name = "BRAND_IMAGE_NO")
	private int brandImage;
	
	@Transient
	private Long itemCount;

	@Override
	public int compareTo(Brand otherBrand) {
		
		return -itemCount.compareTo(otherBrand.getItemCount());
	}
	
}

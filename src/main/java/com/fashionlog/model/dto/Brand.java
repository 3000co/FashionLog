package com.fashionlog.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fashionlog.model.dao.ItemRepository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Brand implements Comparable<Brand> {
	@Transient
	@Autowired
	ItemRepository itemRepo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brandNo;
	@Column(columnDefinition ="char")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "BRAND_IMAGE_NO")
	private File brandImageNo;
	
	@Transient
	private Long itemCount;

	public Long getItemCount() {
		if(this.itemCount==null) {
			this.itemCount = itemRepo.countByBrandNo(this);
			
		}
		return this.itemCount;
	}
	
	@Override
	public int compareTo(Brand otherBrand) {
		//아이템 수로 오름차순 정렬
		return -itemCount.compareTo(otherBrand.getItemCount());
	}
	
}

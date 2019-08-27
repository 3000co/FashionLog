package com.fashionlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Brand;


@Service
public class RankingServiceImpl implements RankingService {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	public void setBrandCount() {
		List<Brand> brandList = brandRepository.findAll();
		for (Brand brand:brandList) {
			brand.setItemCount(itemRepository.countByBrandNo(brand));
		}
	}
	
	
}

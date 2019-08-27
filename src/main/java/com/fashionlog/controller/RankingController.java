package com.fashionlog.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.service.RankingService;
import com.fashionlog.model.service.RankingServiceImpl;

@Controller
public class RankingController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@RequestMapping("ranking/member")
	public String memberRanking(Model model) {
		
		return "ranking/Ranking";
	}
	
	@RequestMapping("ranking/brand")
	@ResponseBody
	public List<Brand> brandRanking(Model model) {
		rankingService.setBrandCount();
		List<Brand> countedBrands = brandRepository.findAll();
		Collections.sort(countedBrands);
		//아이템 많은 수 상위 3개
		return countedBrands.subList(0, 3);
	}
}

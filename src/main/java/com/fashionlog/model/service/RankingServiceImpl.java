package com.fashionlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;


@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private PostService postService;
	@Autowired
	private MemberService memberService;
	
	//매 새벽 3시마다 실행
	@Scheduled(cron = "0 0 3 * * ?")
	public void setBrandCount() {
		System.out.println("brand counted");
		List<Brand> brandList = brandRepository.findAll();
		for (Brand brand:brandList) {
			brand.setItemCount(itemRepository.countByBrandNo(brand));
		}
	}
	
	//매 새벽 3시마다 실행
	@Scheduled(cron = "0 0 3 * * ?")
	public void setLikesCount() {
		System.out.println("likes counted");
		postService.countLikes();
		memberService.countLikes();
	}
	
	
}

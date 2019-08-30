package com.fashionlog.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.service.RankingService;

@Controller
public class RankingController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@RequestMapping("ranking/user/likes")
	public String memberRankingByLikes(Model model) {
		//테스트 끝나면 삭제 (스케줄링으로 구현)
		rankingService.setLikesCount();
		List<Member> countedMembers = memberRepository.findAll();
		Collections.sort(countedMembers,new Comparator<Member>() {
			public int compare(Member m1, Member m2) {
				if(m1.getLikesCount() > m2.getLikesCount()) {
					return -1;					
				}else if(m1.getLikesCount() < m2.getLikesCount()) {
					return 1;
				}else {
					return 0;
				}
			}
		});
		model.addAttribute("rankType","user");
		model.addAttribute("criterion","likes");
		//멤버 10명 넘으면 이걸로
//		model.addAttribute("userRankingList", countedMembers.subList(0,10));
		model.addAttribute("userRankingList", countedMembers);
		return "ranking/Ranking";
	}

	@RequestMapping("ranking/user/followers")
	public String memberRankingByFollowers(Model model) {
		List<Member> members = memberRepository.findAll();
		Collections.sort(members,new Comparator<Member>() {
			public int compare(Member m1, Member m2) {
				if(m1.getFollowers().size() > m2.getFollowers().size()) {
					return -1;					
				}else if(m1.getFollowers().size() < m2.getFollowers().size()) {
					return 1;
				}else {
					return 0;
				}
			}
		});
		model.addAttribute("rankType","user");
		model.addAttribute("criterion","followers");
		//멤버 10명 넘으면 이걸로
//		model.addAttribute("userRankingList", countedMembers.subList(0,10));
		model.addAttribute("userRankingList", members);
		return "ranking/Ranking";
	}
	
	@RequestMapping("ranking/brand")
	public String brandRanking(Model model) {
		//테스트 끝나면 삭제 (스케줄링으로 구현)
		rankingService.setBrandCount();
		List<Brand> countedBrands = brandRepository.findAll();
		Collections.sort(countedBrands);
		model.addAttribute("rankType","brand");
		//아이템 많은 수 상위 10개
		model.addAttribute("brandRankingList", countedBrands.subList(0, countedBrands.size() > 10 ? 10 : countedBrands.size()));
		return "ranking/Ranking";
	}
}
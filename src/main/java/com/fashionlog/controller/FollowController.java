package com.fashionlog.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.FollowRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.service.FollowService;
import com.fashionlog.model.service.NotificationService;
import com.fashionlog.security.SecurityUser;

@Controller
public class FollowController {
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/checkFollow")
	@ResponseBody
	public String checkFollow(Integer memberNo, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		Member followee = memberRepository.findByMemberNo(memberNo);
		if(memberNo == user.getMemberNo()) {
			return "self";
		}
		return followService.isFollowing(user, followee) ? "following" : "follow";
	}
	
	@RequestMapping("/doFollow")
	@ResponseBody
	public void follow(Integer memberNo, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		Member followee = memberRepository.findById(memberNo).get();
		if(!followService.isFollowing(user, followee)) {
			Follow follow = new Follow();
			follow.setFollowerMemNo(user);
			follow.setFolloweeMemNo(followee);
			followRepository.save(follow);
			notificationService.enrollNotification(follow);
		}
		
		
	}
	
	@RequestMapping("/unFollow")
	@ResponseBody
	public void unfollow(Integer memberNo, @AuthenticationPrincipal SecurityUser securityUser) {
//		public void unfollow(Integer memberNo, HttpSession session) {
		Member user = securityUser.getMember();
		Member followee = memberRepository.findById(memberNo).get();
		Optional<Follow> followEvent = followRepository.findByFollowerMemNoAndFolloweeMemNo(user, followee);
		if(followEvent.isPresent()) {
			followRepository.delete(followEvent.get());
		}
	}
}


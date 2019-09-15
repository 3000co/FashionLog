package com.fashionlog.booting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fashionlog.model.service.LikesService;
import com.fashionlog.model.service.RankingService;

@Component
public class TransientValuesSetter implements ApplicationRunner {
	
	@Autowired
	private LikesService likesService;
	@Autowired
	private RankingService rankingService;
	
	public void bootSetter() {
	likesService.countLikes();
	rankingService.setBrandCount();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("bootSetter runnig");
		bootSetter();
	}
	
}

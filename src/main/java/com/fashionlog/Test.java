package com.fashionlog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.fashionlog.model.dao.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test{
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void testDateInsert(){
		
	}
	
	
	
	
	
}

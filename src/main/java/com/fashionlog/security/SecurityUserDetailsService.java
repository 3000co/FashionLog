package com.fashionlog.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.Member2Repository;
import com.fashionlog.model.dto.Member2;

@Service
public class SecurityUserDetailsService implements UserDetailsService{
	@Autowired
	private Member2Repository member2Repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member2> optional = member2Repo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username+" 사용자 없음");
		}else {
			Member2 member2 = optional.get();
		return new SecurityUser(member2);

		
		//		Optional<Member> optional = memberRepo.findById(id);
//		if(!optional.isPresent()) {
//			throw new UsernameNotFoundException(id+" 사용자 없음");
//		}else {
//			Member member = optional.get();
//		
//		return new SecurityUser(member);
	}
		
	}

}

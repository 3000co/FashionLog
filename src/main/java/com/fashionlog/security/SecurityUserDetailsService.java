package com.fashionlog.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member user = memberRepository.findById(id);
		if (user != null) {
			return new SecurityUser(user);
		} else {
			System.out.println("ererer");
			throw new UsernameNotFoundException(id + " : 사용자 없음");
		}
		
		
		
	}

}

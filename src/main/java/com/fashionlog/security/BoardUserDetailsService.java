package com.fashionlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.MemberRepository;

import java.util.Optional;
import com.fashionlog.model.dto.Member;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public UserDetails loadUserByUsername(String Id) throws UsernameNotFoundException {
		Member member = memberRepo.findById(Id);
		if (member != null) {
			throw new UsernameNotFoundException(Id + " 사용자 없음");
		} else {
			 member = memberRepo.findById(Id);
			return new SecurityUser();
		}
	}
}

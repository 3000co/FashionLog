package com.fashionlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dto.Member;
import java.util.Optional;


@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepo.findById(id);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(id + " 사용자 없음");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}
}

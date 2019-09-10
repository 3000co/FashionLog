package com.fashionlog.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.fashionlog.model.dto.Member2;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;
	
	private Member2 member2;

	   public SecurityUser(Member2 member2) {
	      super(member2.getId(), member2.getPassword(), AuthorityUtils.createAuthorityList(member2.getRole().toString()));

	      this.member2 = member2;
	   }

	   public Member2 getMember2() {
	      return member2;
	   }

	   

}

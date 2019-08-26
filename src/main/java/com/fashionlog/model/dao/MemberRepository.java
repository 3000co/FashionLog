package com.fashionlog.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	public Member findById(String id);
	
	@Query(value= "insert into Member(id,password,nickname,phonenumber,email,profile_Image_No,style_No1) values (#{id},'1','1','1','1','1','1')",nativeQuery = true)
	public void setJoin(Member member);
	
	
//	public Member getMemberInfo(Member member);

		
	
}

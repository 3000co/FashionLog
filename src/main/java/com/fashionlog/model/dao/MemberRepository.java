package com.fashionlog.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
	@Query(value = "select id from Member where member.id=:id and member.password=:password" , nativeQuery = true)
	public Optional<Member> findById(char id);
	
	@Query(value= "insert into Member(id,password,nickname,phonenumber,email,profile_Image_No,style_No1) values ('#{id}','#{password}','#{nickname}','#{phonenumber}','#{email}','1','1')",nativeQuery = true)
	public void setJoin(Member member);
	
	@Query(value="select id from Member")
	public Member getMemberInfo(Member member);
}


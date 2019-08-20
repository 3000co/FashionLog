package com.fashionlog.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	private int memberNo;
	private String id;
	private String password;
	private String nickname;
	private String phonenumber;
	private String email;
	private int profileImageNo;
	private int styleNo1;
	private int styleNo2;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getProfileImageNo() {
		return profileImageNo;
	}
	public void setProfileImageNo(int profileImageNo) {
		this.profileImageNo = profileImageNo;
	}
	public int getStyleNo1() {
		return styleNo1;
	}
	public void setStyleNo1(int styleNo1) {
		this.styleNo1 = styleNo1;
	}
	public int getStyleNo2() {
		return styleNo2;
	}
	public void setStyleNo2(int styleNo2) {
		this.styleNo2 = styleNo2;
	}
	
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", id=" + id + ", password=" + password + ", nickname=" + nickname
				+ ", phonenumber=" + phonenumber + ", email=" + email + ", profileImageNo=" + profileImageNo
				+ ", styleNo1=" + styleNo1 + ", styleNo2=" + styleNo2 + "]";
	}
	
}

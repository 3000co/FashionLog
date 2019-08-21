package com.fashionlog.model.dto;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class CommentFileMember {
	private int commentNo;
	private String contents;
	private char nickname;
	private int profileImageNo;
	private String path;
}

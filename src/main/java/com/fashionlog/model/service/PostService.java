package com.fashionlog.model.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

	public String insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception;
}

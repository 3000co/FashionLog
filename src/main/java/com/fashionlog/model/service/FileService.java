package com.fashionlog.model.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dto.File;

public interface FileService {
	
	public File insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception;

}
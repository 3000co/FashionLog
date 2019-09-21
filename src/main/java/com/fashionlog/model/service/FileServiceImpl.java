package com.fashionlog.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dto.File;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileRepository fileRepository;
	@Value("${file.uploadPath}")
	private String uploadPath;
	@Value("${file.appPath}")
	private String appPath;


	/**
	 * uploadPath 변수가 상대경로일 필요성 uploadPath 가 db저장 될 때, 이미지를 뿌려줄 때도 쓸 수 있는 형태인지 파일명이나
	 * 경로가 너무 길어서 db에 안들어갈 때 handling
	 */
	@Override
	public File insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		Date now = new Date();
		String savedName = uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes(), now);

		File file = new File();
		file.setType(request.getParameter("type"));
		file.setName(savedName);
		file.setPath(uploadPath + "/" + savedName);
		file.setSize((int) mulFile.getSize());
		System.err.println("db용 파일 : " + file.toString());
		fileRepository.save(file);
		File fileName = fileRepository.findByName(savedName);
		return fileName;
	}

	private String uploadFile(String originalName, byte[] fileData, Date now) throws Exception {
		SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
		String savedName = uid.format(now) + "_" + originalName;
		String absolutePath = new java.io.File("").getAbsolutePath();
		java.io.File target = new java.io.File(absolutePath+appPath+uploadPath+"/"+savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}


}

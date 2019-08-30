package com.fashionlog.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.File;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private FileRepository fileRepository;
	@Value("${file.uploadPath}")
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	@Override
	public String insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		logger.info("originalName:" + mulFile.getOriginalFilename());
		logger.info("size:"+mulFile.getSize());
		logger.info("contentType:"+mulFile.getContentType());
		Date now = new Date();
		String savedName = uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes(), now, mulFile );

		File file = new File();
		file.setType(request.getParameter("type"));
		file.setName(savedName);
		file.setPath(uploadPath+"/"+savedName);
		file.setSize((int) mulFile.getSize());
		System.out.println(file.toString());
		fileRepository.save(file);

		return savedName;
	}
	
	private String uploadFile(String originalName,byte[] fileData, Date now, MultipartFile mulFile ) throws Exception{
		SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
		String savedName = uid.format(now)+"_"+mulFile.getOriginalFilename();		

		java.io.File target = new java.io.File(uploadPath,savedName);

		FileCopyUtils.copy(fileData, target);
		return savedName;
	}

}

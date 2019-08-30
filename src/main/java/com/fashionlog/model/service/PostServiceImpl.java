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
public class PostServiceImpl implements PostService {
	@Autowired
	private FileRepository fileRepository;
	@Value("${file.uploadPath}")
	private String uploadPath;

	@Override
	public File insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		Date now = new Date();
		String savedName = uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes(), now, mulFile );

		File file = new File();
		file.setType(request.getParameter("type"));
		file.setName(savedName);
		file.setPath(uploadPath+"/"+savedName);
		file.setSize((int) mulFile.getSize());
		fileRepository.save(file);
		
		File fileName = fileRepository.findByName(savedName);
		
		return fileName;
	}
	
	private String uploadFile(String originalName,byte[] fileData, Date now, MultipartFile mulFile ) throws Exception{
		SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
		String savedName = uid.format(now)+"_"+mulFile.getOriginalFilename();		

		java.io.File target = new java.io.File(uploadPath,savedName);

		FileCopyUtils.copy(fileData, target);
		return savedName;
	}

}
package com.fashionlog.controller;





import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.CommentRepository;
import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dto.File;

@Controller
public class FileController {
	@Autowired
	FileRepository fileRepository;
	@Value("${file.uploadPath}")
	String uploadPath;
	
	private static final Logger logger =
			LoggerFactory.getLogger(FileController.class);

		
		@RequestMapping("/file")
			public String moveView() {
				return "uploadForm";
			}
		
		@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
		public String uploadForm(MultipartFile mulFile,Model model, HttpServletRequest request) throws Exception {
			logger.info("originalName:" + mulFile.getOriginalFilename());
			logger.info("size:"+mulFile.getSize());
			logger.info("contentType:"+mulFile.getContentType());
//			String savedName = 
//					uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes());
//			model.addAttribute("savedName", savedName);
			Date now = new Date();
			SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
			String savedName = uid.format(now)+"_"+mulFile.getOriginalFilename();		
			
			
				File file = new File();
				//이미지 등록한 곳이 프로필 사진 변경 창인지 포스트 등록창인지에 따라 TYPE 변해야 함.
				
				file.setType("post");
				file.setName(savedName);
				file.setPath(uploadPath+"/"+savedName);
				file.setSize((int) mulFile.getSize());
				file.setUploadTime(now);
				System.out.println(file.toString());
				fileRepository.save(file);
			
			
			return "redirect:/file";
		}
		
//		private String uploadFile(String originalName, byte[] fileData)throws Exception{
//			UUID uid = UUID.randomUUID();
//			String savedName = uid.toString() +"_"+originalName;
//			java.io.File target = new java.io.File(uploadPath,savedName);
//			FileCopyUtils.copy(fileData, target);
//			return savedName;
//		}
	
}

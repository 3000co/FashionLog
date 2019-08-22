package com.fashionlog.controller;




import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


public class FileController {
	@Controller
	public static class UploadController {
		
		private static final Logger logger =
				LoggerFactory.getLogger(UploadController.class);
		
		@Value("${file.uploadPath}")
		String uploadPath;
		
		@RequestMapping("/file")
			public String moveView() {
				return "uploadForm";
			}
		
		@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
		public void uploadForm(MultipartFile file,Model model) throws IOException, Exception {
			logger.info("originalName:" + file.getOriginalFilename());
			logger.info("size:"+file.getSize());
			logger.info("contentType:"+file.getContentType());
			String savedName = 
					uploadFile(file.getOriginalFilename(), file.getBytes());
			model.addAttribute("savedName", savedName);
		}
		
		private String uploadFile(String originalName, byte[] fileData)throws Exception{
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString() +"_"+originalName;
			File target = new File(uploadPath,savedName);
			FileCopyUtils.copy(fileData, target);
			return savedName;
		}
	}
}

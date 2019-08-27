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
import org.springframework.web.bind.annotation.RequestParam;
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
		
		@RequestMapping(value = "/insertFile", method = RequestMethod.POST)
		public String insertFile(MultipartFile mulFile,Model model, HttpServletRequest request) throws Exception {
			logger.info("originalName:" + mulFile.getOriginalFilename());
			logger.info("size:"+mulFile.getSize());
			logger.info("contentType:"+mulFile.getContentType());
			Date now = new Date();
			String savedName = 
					uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes(), now, mulFile );
//			model.addAttribute("savedName", savedName);
			
				File file = new File();
				file.setType(request.getParameter("type"));
				file.setName(savedName);
				file.setPath(uploadPath+"/"+savedName);
				file.setSize((int) mulFile.getSize());
				System.out.println(file.toString());
				fileRepository.save(file);
			
				return "redirect:/file";
		}
		
		private String uploadFile(String originalName,byte[] fileData, Date now, MultipartFile mulFile )
				throws Exception{
//			UUID uid = UUID.randomUUID();
//			String savedName = uid.toString() +"_"+originalName;
			SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
			String savedName = uid.format(now)+"_"+mulFile.getOriginalFilename();		
			
			java.io.File target = new java.io.File(uploadPath,savedName);
			
			FileCopyUtils.copy(fileData, target);
			return savedName;
		}
		
		@RequestMapping(value = "/deleteFile")
		public String deleteFile(@RequestParam("fileNo")int fileNo) {
			File target = fileRepository.findById(fileNo).get();
			this.uploadPath = uploadPath+"/"+target.getName();
			java.io.File file = new java.io.File(uploadPath);
			if(file.exists()) {
	            file.delete();
	        }
			fileRepository.deleteById(fileNo);
			
			return "redirect:/file";
			
		}
		
}

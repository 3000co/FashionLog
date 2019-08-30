package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.File;

public interface FileRepository extends JpaRepository<File, Integer>{
	List<File> findByType(char type);
	File findByName(String name);
}

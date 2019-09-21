package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fashionlog.model.dto.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

}

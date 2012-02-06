package com.swg.core.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swg.core.entity.SiteReportBean;

@Repository
public interface SiteReportRepository extends
		JpaRepository<SiteReportBean, Integer> {
}

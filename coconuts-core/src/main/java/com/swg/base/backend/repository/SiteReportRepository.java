package com.swg.base.backend.repository;

import com.swg.base.backend.entity.SiteReportBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteReportRepository extends
        JpaRepository<SiteReportBean, Integer> {
}

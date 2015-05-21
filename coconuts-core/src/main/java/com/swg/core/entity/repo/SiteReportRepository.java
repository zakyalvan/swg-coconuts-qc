package com.swg.core.entity.repo;

import com.swg.core.entity.SiteReportBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteReportRepository extends
        JpaRepository<SiteReportBean, Integer> {
}

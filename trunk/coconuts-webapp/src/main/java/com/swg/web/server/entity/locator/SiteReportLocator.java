package com.swg.web.server.entity.locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.core.entity.SiteReport;
import com.swg.core.entity.SiteReportBean;
import com.swg.core.entity.repo.SiteReportRepository;

/**
 * Locator untuk entity object yang nyimpan data laporan lapangan dari pemantau {@link SiteReport}.
 * Kelas ini diperlukan untuk client berbasis gwt (Fitur request factory).
 * 
 * @author zakyalvan
 */
@Component
@Configurable
@Scope("prototype")
public class SiteReportLocator extends Locator<SiteReportBean, Integer> {
	@Autowired(required=true)
	private SiteReportRepository repository;
	
	@Override
	public SiteReportBean create(Class<? extends SiteReportBean> clazz) {
		return new SiteReportBean();
	}
	@Override
	public SiteReportBean find(Class<? extends SiteReportBean> clazz, Integer id) {
		return repository.findOne(id);
	}
	@Override
	public Class<SiteReportBean> getDomainType() {
		return SiteReportBean.class;
	}
	@Override
	public Integer getId(SiteReportBean domainObject) {
		return domainObject.getId();
	}
	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}
	@Override
	public Object getVersion(SiteReportBean domainObject) {
		return domainObject.getVersion();
	}
}

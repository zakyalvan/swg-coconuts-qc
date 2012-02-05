package com.swg.core.initiator.mapper;

import org.apache.log4j.Logger;

import com.swg.core.entity.VoteObserver;
import com.swg.core.initiator.ContentData;
import com.swg.core.initiator.parameter.TextParameter;
import com.swg.core.util.EntityKeywordUtil;

public class VoteObserverEntityMapperImpl implements VoteObserverEntityMapper {

	private static final Logger logger=Logger.getLogger(VoteObserverEntityMapperImpl.class);
	
	@Override
	public VoteObserver mapFromExtern(Object data) {
		ContentData content=(ContentData)data;
		if(content==null)
			throw new RuntimeException("conversion ignored because content data is null");
		VoteObserver observer = null;
		TextParameter nameParam=(TextParameter) content.getExternalData(EntityKeywordUtil.NAMA_LENGKAP);
		TextParameter cellularParam=(TextParameter)content.getExternalData(EntityKeywordUtil.SELULER);
		if(!nameParam.isProceed() && !cellularParam.isProceed()){
			observer=new VoteObserver();
			observer.setFullName(nameParam.getValue());
			observer.setCellularNumber(cellularParam.getValue());
			nameParam.setProceed(true);
			nameParam.setProceed(true);
		}
		logger.info("observer fullname: "+observer.getFullName());
		logger.info("observer cellular no: "+observer.getCellularNumber());
	
		return observer;
	}

}

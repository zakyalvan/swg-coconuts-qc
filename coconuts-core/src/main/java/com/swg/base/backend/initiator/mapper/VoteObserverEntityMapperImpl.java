package com.swg.base.backend.initiator.mapper;

import com.swg.base.backend.entity.VoteObserverBean;
import com.swg.base.backend.initiator.ContentData;
import com.swg.base.backend.initiator.parameter.TextParameter;
import com.swg.base.backend.util.EntityKeywordUtil;
import org.apache.log4j.Logger;

public class VoteObserverEntityMapperImpl implements VoteObserverEntityMapper {

    private static final Logger logger = Logger.getLogger(VoteObserverEntityMapperImpl.class);

    @Override
    public VoteObserverBean mapFromExtern(Object data) {
        ContentData content = (ContentData) data;
        if (content == null)
            throw new RuntimeException("conversion ignored because content data is null");
        VoteObserverBean observer = null;
        TextParameter nameParam = (TextParameter) content.getExternalData(EntityKeywordUtil.NAMA_LENGKAP);
        TextParameter cellularParam = (TextParameter) content.getExternalData(EntityKeywordUtil.SELULER);
        if (!nameParam.isProceed() && !cellularParam.isProceed()) {
            observer = new VoteObserverBean();
            observer.setFullName(nameParam.getValue());
            observer.setCellularNumber(cellularParam.getValue());
            nameParam.setProceed(true);
            nameParam.setProceed(true);
        }
        logger.info("observer fullname: " + observer.getFullName());
        logger.info("observer cellular no: " + observer.getCellularNumber());

        return observer;
    }

}

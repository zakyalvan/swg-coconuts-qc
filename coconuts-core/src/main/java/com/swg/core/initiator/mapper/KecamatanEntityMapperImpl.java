package com.swg.core.initiator.mapper;

import com.swg.core.entity.Kecamatan;
import com.swg.core.initiator.ContentData;
import com.swg.core.initiator.parameter.TextParameter;
import com.swg.core.util.EntityKeywordUtil;
import org.apache.log4j.Logger;


public class KecamatanEntityMapperImpl implements WilayahEntityMapper<Kecamatan> {

    private static final Logger logger = Logger.getLogger(KecamatanEntityMapperImpl.class);

    @Override
    public Kecamatan mapFromExtern(Object data) {
        ContentData content = (ContentData) data;
        if (content == null)
            throw new RuntimeException("conversion ignored because content data is null");
        Kecamatan kecamatan = null;
        TextParameter nameParameter = (TextParameter) content.getExternalData(EntityKeywordUtil.KECAMATAN);
        if (!nameParameter.isProceed()) {
            kecamatan = new Kecamatan();
            kecamatan.setNama(nameParameter.getValue());
            nameParameter.setProceed(true);
        }
        logger.info("kecamatan name: " + kecamatan.getNama());
        return kecamatan;
    }

}

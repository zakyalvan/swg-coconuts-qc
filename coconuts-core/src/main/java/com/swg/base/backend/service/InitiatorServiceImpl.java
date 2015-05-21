/**
 *
 */
package com.swg.base.backend.service;

import com.swg.base.backend.initiator.Initiator;
import com.swg.base.backend.initiator.WorkBookInitiator;
import com.swg.base.backend.initiator.extractor.RowExtractor;
import com.swg.base.backend.initiator.mapper.EntityMapper;
import com.swg.base.backend.initiator.mapper.HeaderParserImpl;
import com.swg.base.backend.initiator.mapper.VoteObserverEntityMapperImpl;
import com.swg.base.backend.initiator.HeaderParser;
import com.swg.base.backend.initiator.buffer.WorkbookBuffer;
import com.swg.base.backend.initiator.buffer.WorkbookBufferImpl;
import com.swg.base.backend.initiator.extractor.RowExtractorImpl;
import com.swg.base.backend.initiator.mapper.KecamatanEntityMapperImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author satriaprayoga
 */

public class InitiatorServiceImpl implements InitiatorService {


    private Initiator initiator;

    public InitiatorServiceImpl() {
    }

    public InitiatorServiceImpl(Initiator initiator) {
        this.initiator = initiator;
    }

    public static void main(String[] args) {
        RowExtractor rowExtractor = new RowExtractorImpl();
        HeaderParser headerParser = new HeaderParserImpl();
        WorkbookBuffer buffer = new WorkbookBufferImpl();
        buffer.setHeaderParser(headerParser);
        buffer.setRowExtractor(rowExtractor);
        List<EntityMapper<?>> entityMappers = new ArrayList<EntityMapper<?>>();
        entityMappers.add(new VoteObserverEntityMapperImpl());
        entityMappers.add(new KecamatanEntityMapperImpl());
        buffer.setEntityMapper(entityMappers);

        Initiator initiator = new WorkBookInitiator();
        initiator.setBuffer(buffer);
        InitiatorServiceImpl initiatorService = new InitiatorServiceImpl(initiator);
        initiatorService.doInitiate("test.xls");
    }

    /* (non-Javadoc)
     * @see com.swg.core.service.InitiatorService#doInitiate(java.io.InputStream)
     */
    @Override
    public void doInitiate(InputStream inputStream) {
        initiator.doInit(inputStream);
    }

    @Override
    public void doInitiate(String fileSource) {
        initiator.doInit(fileSource);
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }


}

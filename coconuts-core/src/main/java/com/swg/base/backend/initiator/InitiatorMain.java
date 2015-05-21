package com.swg.base.backend.initiator;

import com.swg.base.backend.initiator.extractor.RowExtractor;
import com.swg.base.backend.initiator.mapper.HeaderParserImpl;
import com.swg.base.backend.initiator.buffer.WorkbookBuffer;
import com.swg.base.backend.initiator.buffer.WorkbookBufferImpl;
import com.swg.base.backend.initiator.extractor.RowExtractorImpl;
import com.swg.base.backend.initiator.mapper.EntityMapper;
import com.swg.base.backend.initiator.mapper.KecamatanEntityMapperImpl;
import com.swg.base.backend.initiator.mapper.VoteObserverEntityMapperImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitiatorMain {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
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
        initiator.doInit("test.xls");

    }

}

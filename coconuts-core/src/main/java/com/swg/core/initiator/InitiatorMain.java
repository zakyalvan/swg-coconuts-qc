package com.swg.core.initiator;

import com.swg.core.initiator.buffer.WorkbookBuffer;
import com.swg.core.initiator.buffer.WorkbookBufferImpl;
import com.swg.core.initiator.extractor.RowExtractor;
import com.swg.core.initiator.extractor.RowExtractorImpl;
import com.swg.core.initiator.mapper.EntityMapper;
import com.swg.core.initiator.mapper.HeaderParserImpl;
import com.swg.core.initiator.mapper.KecamatanEntityMapperImpl;
import com.swg.core.initiator.mapper.VoteObserverEntityMapperImpl;

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

/**
 * 
 */
package com.swg.core.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.swg.core.initiator.HeaderParser;
import com.swg.core.initiator.Initiator;
import com.swg.core.initiator.WorkBookInitiator;
import com.swg.core.initiator.buffer.WorkbookBuffer;
import com.swg.core.initiator.buffer.WorkbookBufferImpl;
import com.swg.core.initiator.extractor.RowExtractor;
import com.swg.core.initiator.extractor.RowExtractorImpl;
import com.swg.core.initiator.mapper.EntityMapper;
import com.swg.core.initiator.mapper.HeaderParserImpl;
import com.swg.core.initiator.mapper.KecamatanEntityMapperImpl;
import com.swg.core.initiator.mapper.VoteObserverEntityMapperImpl;

/**
 * @author satriaprayoga
 *
 */

public class InitiatorServiceImpl implements InitiatorService {

	
	private Initiator initiator;
	
	public InitiatorServiceImpl() {
	}
	
	public InitiatorServiceImpl(Initiator initiator) {
		this.initiator=initiator;
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

	public void setInitiator(Initiator initiator) {
		this.initiator = initiator;
	}
	
	public Initiator getInitiator() {
		return initiator;
	}
	
	public static void main(String[] args){
		RowExtractor rowExtractor=new RowExtractorImpl();
		HeaderParser headerParser=new HeaderParserImpl();
		WorkbookBuffer buffer=new WorkbookBufferImpl();
		buffer.setHeaderParser(headerParser);
		buffer.setRowExtractor(rowExtractor);
		List<EntityMapper<?>> entityMappers=new ArrayList<EntityMapper<?>>();
		entityMappers.add(new VoteObserverEntityMapperImpl());
		entityMappers.add(new KecamatanEntityMapperImpl());
		buffer.setEntityMapper(entityMappers);
		
		Initiator initiator=new WorkBookInitiator();
		initiator.setBuffer(buffer);
		InitiatorServiceImpl initiatorService=new InitiatorServiceImpl(initiator);
		initiatorService.doInitiate("test.xls");
	}

	
}

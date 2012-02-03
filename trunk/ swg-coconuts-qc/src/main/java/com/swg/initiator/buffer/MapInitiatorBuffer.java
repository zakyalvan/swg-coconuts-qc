package com.swg.initiator.buffer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swg.initiator.buffer.processor.BufferProcessor;
import com.swg.initiator.convert.EntityConverter;
import com.swg.initiator.convert.MappableConverter;

/**
 * The Implementation of {@link com.swg.initiator.buffer.CollectionInitiatorBuffer}
 * to handle the xls Conversion into collection instance
 * @author satriaprayoga
 *
 */
@Component
public class MapInitiatorBuffer implements CollectionInitiatorBuffer{
	
	private static final Logger logger=Logger.getLogger(MapInitiatorBuffer.class);
	
	/**
	 * The converter will convert the contains of selected sheet on workbook into Map instance
	 * @see {@link com.swg.initiator.convert.MappableConverter}
	 */
	@Autowired
	private MappableConverter<List<String>> converter;
	
	@Autowired
	private BufferProcessor bufferProcessor;
	
	private Workbook workbook;

	@Override
	public void processBuffer(int sheetNumber) throws IOException {
		HSSFSheet sheet=(HSSFSheet)workbook.getSheetAt(sheetNumber);
		int rows=XlsUtil.countRow(sheet);
		logger.info("total logical rows: "+rows);
		for(int i=1;i<=rows;i++){
			HSSFRow row=(HSSFRow)sheet.getRow(i);
			int cells=XlsUtil.countCell(row);
			logger.info("total logical cell: "+cells);
			Map<Object,List<String>> data=(Map<Object, List<String>>) converter.convert(row);
			bufferProcessor.doProcess(data);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setEntityConverter(EntityConverter<?> converter) {
		this.converter=(MappableConverter<List<String>>) converter;
	}

	@Override
	public void setBufferProcessor(BufferProcessor bufferProcessor) {
		this.bufferProcessor=bufferProcessor;
	}
	

	@Override
	public void setWorkBook(Workbook workbook) {
		this.workbook=workbook;
	}

	public Workbook getWorkbook() {
		return workbook;
	}
	

}

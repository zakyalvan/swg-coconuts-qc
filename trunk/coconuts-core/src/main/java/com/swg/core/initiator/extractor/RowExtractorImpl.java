package com.swg.core.initiator.extractor;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.swg.core.initiator.ContentData;
import com.swg.core.initiator.HeaderData;
import com.swg.core.initiator.parameter.CellParameter;
import com.swg.core.initiator.parameter.CellParameterBuilder;

/**
 * @author satriaprayoga
 *
 */

public class RowExtractorImpl implements RowExtractor{

	private static final Logger logger=Logger.getLogger(RowExtractorImpl.class);

	
	@Override
	public Object extract(Row row, HeaderData headerData) {
		ContentData content=new ContentData();
		content.setHeaderData(headerData);
		Iterator<Cell> iterator=row.iterator();
		while(iterator.hasNext()){
			HSSFCell cell=(HSSFCell)iterator.next();
			CellParameter<?> param = null;
			if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
			{
				logger.info("parsing numeric value");
				Double value=cell.getNumericCellValue();
				param=CellParameterBuilder.createCellParameter(Double.class,value);
				logger.info("numeric value parsed: "+param.getValue());
			}else if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
				logger.info("parsing string value");
				String value=cell.getStringCellValue();
				param=CellParameterBuilder.createCellParameter(String.class,value);
				logger.info("string value parsed: "+param.getValue());
			
			}else{
				break;
			}
			String key=content.getHeaderData().getHeaders().get(cell.getColumnIndex());
			logger.info("put key: "+key+" with parameters: "+param.getValue());
			content.putData(key, param);
			
		}
		return content;
	}

}

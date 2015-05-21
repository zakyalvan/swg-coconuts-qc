/**
 *
 */
package com.swg.core.initiator.mapper;

import com.swg.core.initiator.ExcelHeaderData;
import com.swg.core.initiator.HeaderData;
import com.swg.core.initiator.HeaderParser;
import com.swg.core.util.XlsFileUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

/**
 * @author satriaprayoga
 */

public class HeaderParserImpl implements HeaderParser {

    private static final Logger logger = Logger.getLogger(HeaderParserImpl.class);

    @Override
    public Object parseHeader(Sheet sheet) {
        HSSFRow row = (HSSFRow) sheet.getRow(0);
        Iterator<Cell> iterator = row.cellIterator();
        HeaderData data = new ExcelHeaderData();
        int headerSize = XlsFileUtil.countCell(row);
        data.setNumOfHeader(headerSize);
        while (iterator.hasNext()) {
            HSSFCell cell = (HSSFCell) iterator.next();
            Integer key = cell.getColumnIndex();
            logger.info(" adding key " + key);
            String value = cell.getStringCellValue();
            logger.info(" adding value: " + value + " with key: " + key);
            data.getHeaders().put(key, cell.getStringCellValue());
        }
        return data;
    }


}

package com.swg.core.initiator.buffer;

import com.swg.core.initiator.ContentData;
import com.swg.core.initiator.HeaderData;
import com.swg.core.initiator.HeaderParser;
import com.swg.core.initiator.extractor.RowExtractor;
import com.swg.core.initiator.mapper.EntityMapper;
import com.swg.core.util.XlsFileUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.List;

/**
 * Implementasi sederhana dari {@link com.swg.core.initiator.buffer.WorkbookBuffer}
 *
 * @author satriaprayoga
 */
public class WorkbookBufferImpl implements WorkbookBuffer {

    private static final Logger logger = Logger.getLogger(WorkbookBufferImpl.class);

    private RowExtractor rowExtractor;

    private HeaderParser headerParser;

    private List<EntityMapper<?>> entityMappers;

    @Override
    public void processBuffer(Sheet sheet) throws IOException {
        int rows = XlsFileUtil.countRow(sheet);
        logger.info("total row number: " + rows);
        HeaderData header = (HeaderData) headerParser.parseHeader(sheet);
        for (int i = 1; i < rows; i++) {
            HSSFRow row = (HSSFRow) sheet.getRow(i);

            if (XlsFileUtil.isEmptyRow(row))
                break;
            int cells = XlsFileUtil.countCell(row);
            logger.info(" total logical cell number: " + cells);
            ContentData data = (ContentData) rowExtractor.extract(row, header);
            for (EntityMapper<?> mapper : entityMappers) {
                mapper.mapFromExtern(data);
            }
        }

    }

    @Override
    public void setRowExtractor(RowExtractor rowExtractor) {
        this.rowExtractor = rowExtractor;
    }

    @Override
    public void setHeaderParser(HeaderParser headerParser) {
        this.headerParser = headerParser;
    }

    @Override
    public void setEntityMapper(List<EntityMapper<?>> entityMappers) {
        this.entityMappers = entityMappers;

    }
}

package com.swg.initiator.convert;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Implementation of {@link com.swg.initiator.convert.MappableConverter}
 *
 * @author satriaprayoga
 */
@Component
public class MapEntityConverter implements MappableConverter<List<String>> {


    private static final Logger logger = Logger.getLogger(MapEntityConverter.class);

    @Override
    public Map<Object, List<String>> convert(Row row) {
        Map<Object, List<String>> map = new HashMap<Object, List<String>>();
        Iterator<Cell> iterator = row.cellIterator();
        List<String> data = new ArrayList<String>();
        while (iterator.hasNext()) {
            HSSFCell cell = (HSSFCell) iterator.next();
            String value = takeAsString(cell);
            logger.info("add value: " + value);
            data.add(value);
        }
        map.put(row.getRowNum(), data);
        return map;
    }


    private String takeAsString(HSSFCell cell) {
        String value = null;
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            value = cell.getStringCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String num = String.valueOf(cell.getNumericCellValue());
            if (num.endsWith(".0")) {
                num = num.replace(".0", "");
            }
            value = num;
        } else {
            value = null;
        }

        return value;

    }


}

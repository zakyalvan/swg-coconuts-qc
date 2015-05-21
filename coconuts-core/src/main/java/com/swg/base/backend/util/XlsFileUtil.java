/**
 *
 */
package com.swg.base.backend.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author satriaprayoga
 */
public class XlsFileUtil {

    public static Workbook loadWorkbook(String file) throws IOException {
        InputStream inputStream = new FileInputStream(new File(file));
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        return workbook;
    }

    public static Workbook loadWorkbook(InputStream inputStream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        return workbook;
    }

    /**
     * Counting rows from the xls sheet
     *
     * @param sheet
     * @return
     */
    public static int countRow(Sheet sheet) {
        int first = sheet.getFirstRowNum();
        int last = sheet.getLastRowNum();
        return last - first;
    }

    /**
     * Counting cell from a row
     *
     * @param row
     * @return
     */
    public static int countCell(Row row) {
        int first = row.getFirstCellNum();
        int last = row.getLastCellNum();
        return last - first;
    }

    public static boolean isEmptyRow(Row row) {
        boolean empty = false;
        if (row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)
            empty = true;
        return empty;
    }

}

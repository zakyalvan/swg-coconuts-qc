package com.swg.initiator.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Utility class for creatin xls base file a.k.a workbook
 * @author satriaprayoga
 *
 */
public final class XlsUtil {
	
	/**
	 * Create workbook from xls file name
	 * @param source the file name
	 * @return
	 * @throws IOException
	 */
	public static HSSFWorkbook createWorkBook(String source) throws IOException{
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(source)));
		return workbook;
	}
	
	/**
	 * Create workbook from inputStream
	 * @param inputStream the inputStream
	 * @return
	 * @throws IOException
	 */
	public static HSSFWorkbook createWorkbook(InputStream inputStream) throws IOException{
		HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
		return workbook;
	}
	
	/**
	 * Counting rows from the xls sheet
	 * @param sheet
	 * @return
	 */
	public static int countRow(Sheet sheet){
		int first=sheet.getFirstRowNum();
		int last=sheet.getLastRowNum();
		return last-first;
	}
	
	/**
	 * Counting cell from a row
	 * @param row
	 * @return
	 */
	public static int countCell(Row row){
		int first=row.getFirstCellNum();
		int last=row.getLastCellNum();
		return last-first;
	}

}

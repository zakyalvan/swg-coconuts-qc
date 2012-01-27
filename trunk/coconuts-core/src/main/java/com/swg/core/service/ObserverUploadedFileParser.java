package com.swg.core.service;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ObserverUploadedFileParser {
	private Workbook workbook;
	
	public ObserverUploadedFileParser() throws Exception {
		workbook = new HSSFWorkbook();
		FileOutputStream output = new FileOutputStream("workbook-example.xls");
		workbook.write(output);
		output.close();
	}
	
	public void parse() {
		
	}
	
	public static void main(String[] args) throws Exception {
		ObserverUploadedFileParser parser = new ObserverUploadedFileParser();
		parser.parse();
	}
}

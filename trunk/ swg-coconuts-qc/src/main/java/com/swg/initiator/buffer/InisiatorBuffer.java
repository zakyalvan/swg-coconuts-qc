/**
 * 
 */
package com.swg.initiator.buffer;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import com.swg.initiator.buffer.processor.BufferProcessor;

/**
 * Buffer to handling the xls file. The Aim of this interface is to convert the content of the xls file into something else,
 * ..do as you like..
 * @author satriaprayoga
 * @param <E>
 *
 */
public interface InisiatorBuffer<T>{
	
	/**
	 * Processing The Sheet on the xls file that being handle
	 * @param sheetNumber the sheet number
	 * @throws IOException
	 */
	public void processBuffer(int sheetNumber) throws IOException;
	
	/**
	 * Set the buffer processor to delegate the process 
	 * @param bufferProcessor
	 */
	public void setBufferProcessor(BufferProcessor bufferProcessor);
	
	/**
	 * The workbook to be handle
	 * @param workbook
	 */
	public void setWorkBook(Workbook workbook);
	
	
}

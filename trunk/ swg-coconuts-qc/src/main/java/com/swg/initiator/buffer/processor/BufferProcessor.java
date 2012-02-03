package com.swg.initiator.buffer.processor;

/**
 * General Buffer Processor to process the Object that had been converted from Workbook
 * @author satriaprayoga
 *
 */
public interface BufferProcessor {
	
	/**
	 * The Processing method
	 * @param objects The object that have been returned after conversion process
	 */
	public  void doProcess(Object objects);

}

/**
 * 
 */
package com.swg.core.initiator.buffer;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * Interface untuk memproses buffering data dari xls
 * @author satriaprayoga
 *
 */
public interface Buffer<T> {
	
	/**
	 * method untuk processing/buffering data pada sheet
	 * @param sheet sheet yang ingin diproses
	 * @throws IOException
	 */
	public void processBuffer(Sheet sheet) throws IOException;
	

}

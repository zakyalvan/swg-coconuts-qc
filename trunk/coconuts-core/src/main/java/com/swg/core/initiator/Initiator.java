package com.swg.core.initiator;

import java.io.InputStream;

import com.swg.core.initiator.buffer.Buffer;

/**
 * 
 * @author satriaprayoga
 *
 */
public interface Initiator {

	public void doInit(InputStream inputStream);
	
	public void doInit(String source);
	
	public void setSheetNumber(int sheetNumber);
	
	public void setBuffer(Buffer<?> buffer);
	
}

package com.swg.initiator.buffer;

import java.util.Collection;

import com.swg.initiator.convert.EntityConverter;

/**
 * The Buffer to handling Collection type converted xls format
 * @author satriaprayoga
 *
 */
public interface CollectionInitiatorBuffer extends InisiatorBuffer<Collection<?>>{

	/**
	 * Set the converter to convert the xls file into Collection extended instance
	 * @param converter
	 */
	public void setEntityConverter(EntityConverter<?> converter);
	
}

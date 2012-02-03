/**
 * 
 */
package com.swg.initiator.convert;

import org.apache.poi.ss.usermodel.Row;

/**
 * The main interface for converting the workbook row content into any possible class instance
 * @author satriaprayoga
 *
 */
public interface EntityConverter<E> {

	/**
	 * Conversion to the Generic template object realization by the <E> template
	 * @param row
	 * @return
	 */
	public E convert(Row row);
}

package com.swg.core.initiator;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * Interface untuk parsing judul pada sheet xls
 * @author satriaprayoga
 *
 */
public interface HeaderParser {
	
	/**
	 * method utama untuk parsing data judul
	 * @param sheet sheet yang akan diparsing isi judulnya
	 * @return something you desired to be
	 */
	public Object parseHeader(Sheet sheet);

}

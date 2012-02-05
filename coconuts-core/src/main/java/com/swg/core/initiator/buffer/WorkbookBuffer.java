package com.swg.core.initiator.buffer;

import java.util.Collection;
import java.util.List;

import com.swg.core.initiator.HeaderParser;
import com.swg.core.initiator.extractor.RowExtractor;
import com.swg.core.initiator.mapper.EntityMapper;


/**
 * Interface extended dari {@link com.swg.core.initiator.buffer.Buffer}
 * bertujuan agar data yang diproses ditampung dalam object yang mengimplementasikan interface Collection
 * @author satriaprayoga
 *
 */
public interface WorkbookBuffer extends Buffer<Collection<?>> {

	/**
	 * Set extractor untuk setiap baris yang diproses
	 * @param rowExtractor
	 */
	public void setRowExtractor(RowExtractor rowExtractor);
	
	/**
	 * Set parser untuk parsing judul
	 * @param headerParser
	 */
	public void setHeaderParser(HeaderParser headerParser);
	
	/**
	 * List entity mappers yang akan digunakan untuk mapping data dari buffer ke berbagai macam Entity (or any object?)
	 * @param entityMappers
	 */
	public void setEntityMapper(List<EntityMapper<?>> entityMappers);
	
	
}

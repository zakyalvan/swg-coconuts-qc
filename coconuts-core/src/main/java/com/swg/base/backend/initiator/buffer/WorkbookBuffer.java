package com.swg.base.backend.initiator.buffer;

import com.swg.base.backend.initiator.extractor.RowExtractor;
import com.swg.base.backend.initiator.mapper.EntityMapper;
import com.swg.base.backend.initiator.HeaderParser;

import java.util.Collection;
import java.util.List;


/**
 * Interface extended dari {@link Buffer}
 * bertujuan agar data yang diproses ditampung dalam object yang mengimplementasikan interface Collection
 *
 * @author satriaprayoga
 */
public interface WorkbookBuffer extends Buffer<Collection<?>> {

    /**
     * Set extractor untuk setiap baris yang diproses
     *
     * @param rowExtractor
     */
    public void setRowExtractor(RowExtractor rowExtractor);

    /**
     * Set parser untuk parsing judul
     *
     * @param headerParser
     */
    public void setHeaderParser(HeaderParser headerParser);

    /**
     * List entity mappers yang akan digunakan untuk mapping data dari buffer ke berbagai macam Entity (or any object?)
     *
     * @param entityMappers
     */
    public void setEntityMapper(List<EntityMapper<?>> entityMappers);


}

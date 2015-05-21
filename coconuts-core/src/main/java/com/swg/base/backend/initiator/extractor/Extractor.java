/**
 *
 */
package com.swg.base.backend.initiator.extractor;

import com.swg.base.backend.initiator.HeaderData;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Interface dasar untuk meng-ekstract data dari Cell
 *
 * @author satriaprayoga
 */
public interface Extractor<T extends Iterable<Cell>> {

    public Object extract(T source, HeaderData headerData);
}

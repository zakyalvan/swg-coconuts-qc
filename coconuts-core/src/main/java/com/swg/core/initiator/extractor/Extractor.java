/**
 *
 */
package com.swg.core.initiator.extractor;

import com.swg.core.initiator.HeaderData;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Interface dasar untuk meng-ekstract data dari Cell
 *
 * @author satriaprayoga
 */
public interface Extractor<T extends Iterable<Cell>> {

    public Object extract(T source, HeaderData headerData);
}

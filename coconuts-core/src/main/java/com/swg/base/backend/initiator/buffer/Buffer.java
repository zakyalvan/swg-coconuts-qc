/**
 *
 */
package com.swg.base.backend.initiator.buffer;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;

/**
 * Interface untuk memproses buffering data dari xls
 *
 * @author satriaprayoga
 */
public interface Buffer<T> {

    /**
     * method untuk processing/buffering data pada sheet
     *
     * @param sheet sheet yang ingin diproses
     * @throws IOException
     */
    public void processBuffer(Sheet sheet) throws IOException;


}

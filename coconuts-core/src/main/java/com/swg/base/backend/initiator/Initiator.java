package com.swg.base.backend.initiator;

import com.swg.base.backend.initiator.buffer.Buffer;

import java.io.InputStream;

/**
 * @author satriaprayoga
 */
public interface Initiator {

    public void doInit(InputStream inputStream);

    public void doInit(String source);

    public void setSheetNumber(int sheetNumber);

    public void setBuffer(Buffer<?> buffer);

}

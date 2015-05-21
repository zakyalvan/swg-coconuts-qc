/**
 *
 */
package com.swg.base.backend.initiator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementasi sederhana dari {@link HeaderData}
 *
 * @author satriaprayoga
 */
public class ExcelHeaderData implements HeaderData {

    private static final long serialVersionUID = 1L;

    private int numOfHeader;

    private Map<Object, String> headers;

    private List<ContentData> contents;

    public ExcelHeaderData() {
        headers = new HashMap<Object, String>();
        contents = new ArrayList<ContentData>();
    }

    /* (non-Javadoc)
     * @see com.swg.core.initiator.mapper.HeaderData#getNumOfHeader()
     */
    @Override
    public int getNumOfHeader() {
        return numOfHeader;
    }

    /* (non-Javadoc)
     * @see com.swg.core.initiator.mapper.HeaderData#setNumOfHeader(int)
     */
    @Override
    public void setNumOfHeader(int numOfHeader) {
        this.numOfHeader = numOfHeader;
    }

    /* (non-Javadoc)
     * @see com.swg.core.initiator.mapper.HeaderData#getHeaders()
     */
    public Map<Object, String> getHeaders() {
        return headers;
    }

    ;

    /* (non-Javadoc)
     * @see com.swg.core.initiator.mapper.HeaderData#contain(java.lang.String)
     */
    @Override
    public boolean contain(String characters) {
        return headers.containsValue(characters);
    }

    /* (non-Javadoc)
     * @see com.swg.core.initiator.mapper.HeaderData#getPosition(java.lang.String)
     */
    @Override
    public int getPosition(Object keyword) {
        Object key = headers.get(keyword);
        if (key instanceof Integer)
            return (Integer) key;
        return 0;
    }

    @Override
    public List<ContentData> getContents() {
        return contents;
    }

    @Override
    public void addChild(ContentData data) {
        contents.add(data);
    }

}

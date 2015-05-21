package com.swg.base.backend.initiator;

import com.swg.base.backend.initiator.parameter.CellParameter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContentData implements Serializable {

    private static final long serialVersionUID = 1L;

    private HeaderData headerData;

    private Map<Object, CellParameter<? extends Serializable>> parameters;

    public ContentData() {
        this.parameters = new HashMap<Object, CellParameter<?>>();
    }

    public void putData(Object key, CellParameter<?> data) {
        this.parameters.put(key, data);
    }

    public CellParameter<?> getExternalData(Object key) {
        return parameters.get(key);
    }

    public HeaderData getHeaderData() {
        return headerData;
    }

    public void setHeaderData(HeaderData headerData) {
        this.headerData = headerData;
        headerData.addChild(this);
    }
}

package com.swg.sms.format;

import com.swg.sms.action.param.Parameter;

import java.io.Serializable;
import java.util.Set;

/**
 * Simple kelas dari object yang nampung format pesan masuk yang bisa dihandle
 * untuk mengexecute action tertentu.
 *
 * @author zakyalvan
 */
public abstract class Format implements Serializable {
    public static final String DEFAULT_START_TAG = "{";
    public static final String DEFAULT_END_TAG = "}";
    private static final long serialVersionUID = -1107375968806033065L;
    protected final String value;
    protected final FormatModel model;
    public Format(String value) {
        if (value == null || value.trim().length() == 0)
            throw new IllegalArgumentException("Parameter format value should not be null or zero length string.");

        this.value = value;
        this.model = createModel(value);
    }

    protected abstract FormatModel createModel(String value);

    public String getValue() {
        return value;
    }

    public final FormatModel getModel() {
        return model;
    }

    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Format other = (Format) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equalsIgnoreCase(other.value))
            return false;
        return true;
    }

    public interface FormatModel {
        String getFormatString();

        /**
         * Retrieve position dari keyword.
         * Index ini ditentukan setelah format di split menjadi beberapa bagian
         * (token format) dan displit berdasarkan delimiter.
         *
         * @return Integer
         */
        Integer getKeywordPosition();

        /**
         * Count seluruh parameter yang didefinisikan dalam format.
         *
         * @return
         */
        Integer countParameters();

        /**
         * Retrieve parameter name yang terdefinisi dalam format.
         *
         * @return
         */
        Set<String> getParametersName();

        String getParameterNameAt(Integer position);

        Integer getParameterPosition(String name);

        Class<? extends Parameter<?>> getParameterTypeAt(Integer position);

        Class<? extends Parameter<?>> getParameterTypeFor(String name);
    }
}

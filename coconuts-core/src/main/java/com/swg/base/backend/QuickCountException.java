package com.swg.base.backend;

/**
 * Base class untuk eksepsi dalam aplikasi ini.
 *
 * @author zakyalvan
 */
public class QuickCountException extends RuntimeException {
    private static final long serialVersionUID = -4478204446896004520L;

    public QuickCountException() {
        super();
    }

    public QuickCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuickCountException(String message) {
        super(message);
    }

    public QuickCountException(Throwable cause) {
        super(cause);
    }

}

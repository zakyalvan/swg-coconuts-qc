package com.swg.sms.action;

/**
 * Simple validation kontrak untuk object action yang dapat divalidate.
 *
 * @param <T>
 * @author zakyalvan
 */
public interface Validatable {
    void validate() throws ActionValidationException;
}

package com.swg.base.backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Kontrak untuk notification object.
 *
 * @author zakyalvan
 */
public interface Notification extends Serializable {
    Integer getId();

    Integer getCode();

    Date getVersion();
}

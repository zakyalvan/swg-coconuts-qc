package com.swg.base.backend.service.support;

import java.io.Serializable;
import java.util.List;

/**
 * Container dari list object T (yang di paging).
 * Dengan sedikit terpaksa di bikin sebagai jembatan untuk gxt.
 *
 * @author zakyalvan
 */
public interface PagedList<T> extends Iterable<T>, Serializable {
    Integer getPage();

    Integer getSize();

    Integer getOffset();

    Long getTotal();

    List<T> getDatas();
}

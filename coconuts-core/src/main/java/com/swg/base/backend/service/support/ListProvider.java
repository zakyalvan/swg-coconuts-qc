package com.swg.base.backend.service.support;

import java.util.List;

/**
 * Kontrak untuk service yang ngembaliin list of data dengan type data T.
 * Mungkin bisa disebut sebagai repository-exposer atau repository facade..
 *
 * @param <T> type data
 * @param <V> version type
 * @author zakyalvan
 */
public interface ListProvider<T> {
    /**
     * List seluruh data dari T.
     *
     * @return {@link List}
     */
    List<T> getList();

    /**
     * List T untuk halaman page sejumlah size.
     *
     * @param page
     * @param size
     * @return {@link PagedList}
     */
    PagedList<T> getPagedList(Integer page, Integer size);
}

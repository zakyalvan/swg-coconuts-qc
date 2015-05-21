package com.swg.core.service.support;

import java.util.Date;
import java.util.List;

/**
 * @param <T>
 * @param <V>
 * @author zakyalvan
 */
public interface TimedListProvider<T> extends ListProvider<T> {
    List<T> getList(Date startTime);

    List<T> getList(Date startTime, Date endTime);
}
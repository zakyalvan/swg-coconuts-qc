package com.swg.core.service.support;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author zakyalvan
 *
 * @param <T>
 * @param <V>
 */
public interface TimedListProvider<T> extends ListProvider<T> {
	List<T> getList(Date startTime);
	List<T> getList(Date startTime, Date endTime);
}
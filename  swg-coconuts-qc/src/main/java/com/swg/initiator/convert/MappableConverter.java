package com.swg.initiator.convert;

import java.util.Map;

/**
 * The {@link com.swg.initiator.convert.EntityConverter} extension to handle workbook row content to Map
 * @author satriaprayoga
 *
 * @param <E>
 */
public interface MappableConverter<E> extends EntityConverter<Map<Object,E>> {

}

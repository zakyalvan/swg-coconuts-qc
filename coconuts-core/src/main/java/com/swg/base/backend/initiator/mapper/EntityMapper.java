/**
 *
 */
package com.swg.base.backend.initiator.mapper;


/**
 * @author satriaprayoga
 */
public interface EntityMapper<T> {

    public T mapFromExtern(Object data);

}

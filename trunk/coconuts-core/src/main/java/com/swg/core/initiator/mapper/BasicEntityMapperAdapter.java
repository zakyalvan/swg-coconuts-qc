/**
 * 
 */
package com.swg.core.initiator.mapper;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

/**
 * @author satriaprayoga
 *
 */
public abstract class BasicEntityMapperAdapter<T extends Serializable> implements EntityMapper<T> {
	
	protected Class<T> mappedClass;
	
	
	protected Repository<T, Integer> repository;
	
	public BasicEntityMapperAdapter() {
		
	}
	
	public BasicEntityMapperAdapter(Class<T> mappedClass){
		this.mappedClass=mappedClass;
	}
	
	public void setJpaRepository(Repository<T, Integer> repository) {
		this.repository = repository;
	}

}

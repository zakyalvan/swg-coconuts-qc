/**
 * 
 */
package com.swg.core.service;

import java.io.InputStream;

/**
 * @author satriaprayoga
 *
 */
public interface InitiatorService {
	
	public void doInitiate(InputStream inputStream);

	public void doInitiate(String fileSource);
}

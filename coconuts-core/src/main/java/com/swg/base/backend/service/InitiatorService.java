/**
 *
 */
package com.swg.base.backend.service;

import java.io.InputStream;

/**
 * @author satriaprayoga
 */
public interface InitiatorService {

    public void doInitiate(InputStream inputStream);

    public void doInitiate(String fileSource);
}

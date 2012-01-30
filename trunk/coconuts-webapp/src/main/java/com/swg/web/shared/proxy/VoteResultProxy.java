package com.swg.web.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.core.entity.VoteResult;
import com.swg.web.shared.proxy.locator.VoteResultLocator;

@ProxyFor(value=VoteResult.class, locator=VoteResultLocator.class)
public interface VoteResultProxy extends EntityProxy {

}

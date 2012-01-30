package com.swg.web.client.event;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.VoteCountDeleteReqEvent.Handler;
import com.swg.web.shared.proxy.VoteResultProxy;

/**
 * Event yang di-fire untuk request penghapusan data hasil perhitungan perolehan suara.
 * 
 * @author zakyalvan
 */
public class VoteCountDeleteReqEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onVoteCountDeleteRequest(VoteCountDeleteReqEvent event);
	}

	public static final Type<Handler> TYPE = new Type<VoteCountDeleteReqEvent.Handler>();
	
	private final List<VoteResultProxy> willDelete = new ArrayList<VoteResultProxy>();
	
	public VoteCountDeleteReqEvent(Object source, List<VoteResultProxy> willDelete) {
		setSource(source);
		this.willDelete.addAll(willDelete);
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return null;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onVoteCountDeleteRequest(this);
	}

	public List<VoteResultProxy> getWillDelete() {
		return willDelete;
	}
}

package com.swg.client.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.VoteCountRequestEvent.Handler;
import com.swg.shared.proxy.VoteResultProxy;

/**
 * Kelas event yang publish oleh view untuk merequest data hasil perhitungan suara.
 * 
 * @author zakyalvan
 */
public class VoteCountRequestEvent extends GwtEvent<Handler> {
	public static abstract class Handler implements EventHandler {
		public abstract void onVoteCountRequest(VoteCountRequestEvent event);
		void setSuccess(VoteCountRequestEvent event) {
			event.setSuccess(true);
		}
		void setFailure(VoteCountRequestEvent event, Throwable failureCause) {
			event.setFailureCause(failureCause);
		}
	}
	
	public static final Type<Handler> TYPE = new Type<VoteCountRequestEvent.Handler>();
	
	private boolean dispatched = false;
	
	/**
	 * Field yang nandain apakah data yang direquest adalah seluruh data atau tidak.
	 */
	private boolean requestFresh = true;
	
	/**
	 * Field yang nandain data paling lama (batas waktu update time) yang direquest.
	 * Ini untuk menghemat payload ke server.
	 */
	private Date updateTimeOffset;
	
	private boolean success = true;
	private List<VoteResultProxy> resultRecords = new ArrayList<VoteResultProxy>();
	private Throwable failureCause;
	
	public VoteCountRequestEvent(Object source) {
		setSource(source);
	}
	public VoteCountRequestEvent(Object source, Date updateTimeOffset) {
		this(source);
		if(updateTimeOffset != null) {
			this.updateTimeOffset = updateTimeOffset;
			this.requestFresh = false;
		}
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onVoteCountRequest(this);
		dispatched = true;
	}
	
	public boolean isRequestFresh() {
		return requestFresh;
	}
	public Date getUpdateTimeOffset() {
		return updateTimeOffset;
	}
	public boolean isDispatched() {
		return dispatched;
	}

	public boolean isSuccess() {
		return success;
	}
	void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<VoteResultProxy> getResultRecords() {
		return resultRecords;
	}

	public Throwable getFailureCause() {
		return failureCause;
	}
	void setFailureCause(Throwable failureCause) {
		this.failureCause = failureCause;
	}
}

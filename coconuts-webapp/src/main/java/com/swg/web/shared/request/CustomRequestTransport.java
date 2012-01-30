package com.swg.web.shared.request;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CustomRequestTransport extends DefaultRequestTransport {
	@Override
	public void send(String payload, TransportReceiver receiver) {
		doBeforeSend();
		super.send(payload, receiver);
	}

	private TransportReceiver wrapTransportReceiver(final TransportReceiver delegate) {
		return new TransportReceiver() {
			@Override
			public void onTransportSuccess(String payload) {
				doOnTransportSuccess(payload);
				delegate.onTransportSuccess(payload);
			}
			@Override
			public void onTransportFailure(ServerFailure failure) {
				doOnTransportFailure(failure);
				delegate.onTransportFailure(failure);
			}
		};
	}

	@Override
	protected RequestCallback createRequestCallback(TransportReceiver receiver) {
		TransportReceiver wrappedReceiver = wrapTransportReceiver(receiver);
		final RequestCallback wrappedRequestCallback = super.createRequestCallback(wrappedReceiver);
		
		return new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				wrappedRequestCallback.onResponseReceived(request, response);
			}			
			@Override
			public void onError(Request request, Throwable exception) {
				wrappedRequestCallback.onError(request, exception);
			}
		};
	}

	private void doBeforeSend() {
		
	}
	private void doOnTransportSuccess(String payload) {
		
	}
	private void doOnTransportFailure(ServerFailure failure) {
		
	}
}

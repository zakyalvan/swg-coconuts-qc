package com.swg.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.swg.web.client.ioc.ClientFactory;

/**
 * Entry point untuk coconuts web application. Kelas spesifik untuk gwt.
 * Sesuai namanya, dari sinilah aplikasi mulai diakses atau digunakan.
 * 
 * @author zakyalvan
 */
public class CoconutsEntryPoint implements EntryPoint {
	private final ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
	@Override
	public void onModuleLoad() {
		CoconutsApplication application = clientFactory.getApplication();
		application.run();
	}
}

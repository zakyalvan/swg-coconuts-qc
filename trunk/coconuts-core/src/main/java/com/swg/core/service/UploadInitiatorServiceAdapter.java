package com.swg.core.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class UploadInitiatorServiceAdapter extends InitiatorServiceImpl {

	
	public abstract void processUpload(HttpServletRequest request,HttpServletResponse response);
	
	@Override
	public void doInitiate(InputStream inputStream) {
		super.doInitiate(inputStream);
	}

	@Override
	public void doInitiate(String fileSource) {
		super.doInitiate(fileSource);
	}
	
	

}

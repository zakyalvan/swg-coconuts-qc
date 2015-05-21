package com.swg.base.backend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public abstract class UploadInitiatorServiceAdapter extends InitiatorServiceImpl {


    public abstract void processUpload(HttpServletRequest request, HttpServletResponse response);

    @Override
    public void doInitiate(InputStream inputStream) {
        super.doInitiate(inputStream);
    }

    @Override
    public void doInitiate(String fileSource) {
        super.doInitiate(fileSource);
    }


}

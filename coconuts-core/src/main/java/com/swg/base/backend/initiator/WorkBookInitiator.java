package com.swg.base.backend.initiator;

import com.swg.base.backend.initiator.buffer.Buffer;
import com.swg.base.backend.initiator.buffer.WorkbookBuffer;
import com.swg.base.backend.util.XlsFileUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WorkBookInitiator implements Initiator {

    private Buffer buffer;

    private int sheetNumber = 0;

    private HSSFWorkbook workbook;

    @Override
    public void doInit(InputStream inputStream) {
        try {
            workbook = (HSSFWorkbook) XlsFileUtil.loadWorkbook(inputStream);
            init();
            inputStream.close();
        } catch (IOException e) {

        }

    }


    @Override
    public void doInit(String source) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File(source));
            workbook = (HSSFWorkbook) XlsFileUtil.loadWorkbook(inputStream);
            init();
            inputStream.close();
        } catch (IOException e) {

        }
    }

    protected void init() throws IOException {
        HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
        buffer.processBuffer(sheet);
    }

    @Override
    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    @Override
    public void setBuffer(Buffer<?> buffer) {
        this.buffer = (WorkbookBuffer) buffer;
    }


}

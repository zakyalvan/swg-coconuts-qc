package com.swg.initiator;

import com.swg.initiator.buffer.CollectionInitiatorBuffer;
import com.swg.initiator.buffer.XlsUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/inisiator-config*.xml");
        CollectionInitiatorBuffer buffer = context.getBean(CollectionInitiatorBuffer.class);
        HSSFWorkbook workbook = XlsUtil.createWorkBook("C:/Users/satriaprayoga/Documents/test.xls");
        buffer.setWorkBook(workbook);
        buffer.processBuffer(0);
    }

}

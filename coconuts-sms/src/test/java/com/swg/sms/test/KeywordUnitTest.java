package com.swg.sms.test;

import com.swg.sms.action.Keyword;
import com.swg.sms.action.SimpleKeyword;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class KeywordUnitTest {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testSimpleKeyword() {
        String keywordValue = "InpuT";
        Keyword keyword = new SimpleKeyword(keywordValue);

        logger.info(keyword);

        Assert.assertNotNull(keyword);

        Assert.assertTrue("'INPUT' tidak match", keyword.tryMatching("INPUT"));
        Assert.assertTrue("'iNpuT' tidak match", keyword.tryMatching("iNpuT"));
        Assert.assertTrue("' InPut' tidak match", keyword.tryMatching(" InPut"));
        Assert.assertFalse("'apalah' tidak match", keyword.tryMatching("apalah"));

        Assert.assertTrue("'INPUT...' tidak match", keyword.tryMatching("INPUT..."));
        Assert.assertTrue("'.,INPUT..:,' tidak match", keyword.tryMatching(".,INPUT..:,"));
    }
}

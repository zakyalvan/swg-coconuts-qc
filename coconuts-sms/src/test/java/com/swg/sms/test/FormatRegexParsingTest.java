package com.swg.sms.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatRegexParsingTest {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testParseDefinedFormat() {
        Pattern validFormatPattern = Pattern.compile("(\\G\\s*?\\{\\s*?(([keyword]{6,8}?)|([parmet]{8,10}\\s*?:\\s*?(string|number|map){1}\\s*?:\\s*?(\\w*?){1}))\\s*?\\}\\s*?)\\1*?");
        Matcher validFormatMatcher = validFormatPattern.matcher(" {keyword}{parameter:string:iniParameter}");

        logger.info("Hit end : " + validFormatMatcher.hitEnd());

        logger.debug("Group count : " + validFormatMatcher.groupCount());
        while (validFormatMatcher.find()) {
            logger.info(validFormatMatcher.group() + " isinya " + validFormatMatcher.group(2) + " jenis parameter " + validFormatMatcher.group(5) + " nama parameter " + validFormatMatcher.group(6));

            if (validFormatMatcher.group(3) != null) {
                logger.info("Keyword");
            } else if (validFormatMatcher.group(4) != null) {
                logger.info("Parameter(type=" + validFormatMatcher.group(5) + ", name=" + validFormatMatcher.group(6) + ")");
            }
        }

        logger.info("Hit end : " + validFormatMatcher.hitEnd());
    }
}

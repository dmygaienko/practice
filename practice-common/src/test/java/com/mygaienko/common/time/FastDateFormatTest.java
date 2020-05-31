package com.mygaienko.common.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 28/09/2016.
 */
public class FastDateFormatTest {

    private static String hyphenDatePattern = "dd-MMM-yyyy";
    private static String slashDatePattern = "dd/MMM/yyyy";

    @Test
    public void testHyphenDatePattern() throws Exception {
        FastDateFormat format = FastDateFormat.getInstance(hyphenDatePattern);
        Date date = format.parse("11-Sep-1990");

        assertEquals("11-Sep-1990", format.format(date));
    }

    @Test
    public void testSlashDatePattern() throws Exception {
        FastDateFormat format = FastDateFormat.getInstance(slashDatePattern);
        Date date = format.parse("11/Sep/1990");

        assertEquals("11/Sep/1990", format.format(date));
    }
}

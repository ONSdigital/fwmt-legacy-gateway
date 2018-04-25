package uk.gov.ons.fwmt.gateway.utility.csvconverter;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class FieldPeriodTest {

    @Test
    public void fieldPeriodToDateGFF() {
        Date date = LegacyCreateJobRequestFactory.fieldPeriodToDates("807", "GFF");
        assertEquals(new Date(118, 6, 31, 23, 59, 59).toString(), date.toString());
    }

    @Test
    public void fieldPeriodToDateLFS() {
        Date date = LegacyCreateJobRequestFactory.fieldPeriodToDates("84K", "LFS");
        assertEquals(new Date(118, 11, 16, 23, 59, 59).toString(), date.toString());
    }
}

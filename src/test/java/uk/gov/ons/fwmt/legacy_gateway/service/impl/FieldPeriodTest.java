package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class FieldPeriodTest {
  void testGFFDate(LocalDateTime expected, String fieldPeriod) {
    LocalDateTime actual = CSVParsingServiceImpl.convertToGFFDate(fieldPeriod);
    assertEquals(expected, actual);
  }

  void testLFSDate(LocalDateTime expected, String fieldPeriod) {
    LocalDateTime actual = CSVParsingServiceImpl.convertToLFSDate(fieldPeriod);
    assertEquals(expected, actual);
  }

  @Test
  public void fieldPeriodToDateGFFforMonthWith31Days() {
    testGFFDate(LocalDateTime.of(2018, 7, 31, 23, 59, 59), "807");
  }

  @Test
  public void fieldPeriodToDateGFFforMonthWith30Days() {
    testGFFDate(LocalDateTime.of(2018, 6, 30, 23, 59, 59), "806");
  }

  @Test
  public void fieldPeriodToDateGFFforFebuary() {
    testGFFDate(LocalDateTime.of(2018, 2, 28, 23, 59, 59), "802");
  }

  @Test
  public void fieldPeriodToDateLFSforQuater4Week11() {
    testLFSDate(LocalDateTime.of(2018, 12, 29, 23, 59, 59), "84K");
  }

  @Test
  public void fieldPeriodToDateLFSforQuater2Week2() {
    testLFSDate(LocalDateTime.of(2018, 4, 28, 23, 59, 59), "82B");
  }
}

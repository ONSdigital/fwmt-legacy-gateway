package uk.gov.ons.fwmt.gateway.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.fwmt.gateway.utility.LegacyFilename;

import static org.junit.Assert.assertEquals;

public class LegacyFilenameParserTest {
  
  private LegacyFilename legacyFilename;
  
  @Autowired
  public LegacyFilenameParserTest(LegacyFilename legacyFilename) {
    this.legacyFilename = legacyFilename;
  }

  @Test
  public void assertValidFilenameSampleTrue() {
    Boolean actual;
    try{
      LegacyFilename.assertValidFilename("sample_GFF_2018-04-24T19:09:54Z.csv", "sample");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = true;
    assertEquals(expected, actual);
  }
  
  @Test
  public void assertValidFilenameSampleFalse() {
    Boolean actual;
    try{
      LegacyFilename.assertValidFilename("BLAH_BLAH-THISIS_WRONG", "sample");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = false;
    assertEquals(expected, actual);
  }
  
  @Test
  public void assertValidFilenameStaffTrue() {
    Boolean actual;
    try{
      LegacyFilename.assertValidFilename("staff_GFF_2018-04-24T19:09:54Z.csv", "staff");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = true;
    assertEquals(expected, actual);
  }
  
  @Test
  public void assertValidFilenameStaffFalse() {
    Boolean actual;
    try{
      LegacyFilename.assertValidFilename("BLAH_BLAH-THISIS_WRONG", "staff");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = false;
    assertEquals(expected, actual);
  }
}

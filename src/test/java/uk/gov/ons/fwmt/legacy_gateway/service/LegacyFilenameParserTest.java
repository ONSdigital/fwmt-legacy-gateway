package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.fwmt.legacy_gateway.service.FileIngestService;

import static org.junit.Assert.assertEquals;

public class LegacyFilenameParserTest {

  private FileIngestService fileIngestService;
  
  @Autowired
  public LegacyFilenameParserTest(FileIngestService fileIngestService) {
    this.fileIngestService = fileIngestService;
  }

  @Test
  public void assertValidFilenameSampleTrue() {
    Boolean actual;
    try{
      fileIngestService.verifyCSVFilename("sample_GFF_2018-04-24T19:09:54Z.csv", "sample");
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
      fileIngestService.verifyCSVFilename("BLAH_BLAH-THISIS_WRONG", "sample");
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
      fileIngestService.verifyCSVFilename("staff_GFF_2018-04-24T19:09:54Z.csv", "staff");
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
      fileIngestService.verifyCSVFilename("BLAH_BLAH-THISIS_WRONG", "staff");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = false;
    assertEquals(expected, actual);
  }
}

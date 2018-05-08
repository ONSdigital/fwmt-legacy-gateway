package uk.gov.ons.fwmt.gateway.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.gov.ons.fwmt.gateway.utility.FileValidation;

public class FileValidationTest {
  
  private FileValidation fileValidation;
  
  @Autowired
  public FileValidationTest(FileValidation fileValidation) {
    this.fileValidation = fileValidation;
  }

  @Test
  public void assertValidFilenameSampleTrue() {
    Boolean actual;
    try{
      fileValidation.assertValidFilename("sample_GFF_2018-04-24T19:09:54Z.csv", "sample");
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
      fileValidation.assertValidFilename("BLAH_BLAH-THISIS_WRONG", "sample");
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
      fileValidation.assertValidFilename("staff_GFF_2018-04-24T19:09:54Z.csv", "staff");
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
      fileValidation.assertValidFilename("BLAH_BLAH-THISIS_WRONG", "staff");
      actual = true;
    } catch (Exception e) {
      actual = false;
    }
    Boolean expected = false;
    assertEquals(expected, actual);
  }
}

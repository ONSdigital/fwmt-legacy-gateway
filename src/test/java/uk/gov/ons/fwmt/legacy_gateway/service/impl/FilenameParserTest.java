package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import org.junit.Test;
import org.mockito.Mockito;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;

import static org.junit.Assert.*;

public class FilenameParserTest {
  private FileIngestServiceImpl fileIngestServiceImpl;

  private final String[] validSampleFileNames = {
      "sample_GFF_2018-04-24T19:09:54Z.csv",
      "sample_GFF_2018-04-24T19-09-54Z.csv",
      "sample_LFS_2018-04-24T19:31:25Z.csv",
      "sample_LFS_2018-04-24T19-31-25Z.csv",
  };

  private final String[] validStaffFileNames = {
      "staff_2016-04-24T19:09:54Z.csv",
      "staff_2018-04-24T19:31:25Z.csv",
      "staff_2018-04-24T19-31-25Z.csv",
  };

  private final String[] invalidSampleFileNames = {
      "BLAH_BLAH-THIS_IS_WRONG",
      "sample_2018-04-24T19-31-25Z.csv",
      "sample_LFS_2018-04-24T19-31-25Z.csp",
  };

  private final String[] invalidStaffFileNames = {
      "BLAH_BLAH-THIS_IS_WRONG",
      "staff_2018-04-24R19-31-25Z.csv",
      "staff_EG_2018-04-24L19-31-25Z.csv",
      "foo_LFS_2018-04-24L19-31-25Z.csv",
      "staff_LFS_2018-04-24T19-31-25Z.csv",
      "staff_2018-04-24T19:31:25Z.csp",
  };

  public FilenameParserTest() {
    this.fileIngestServiceImpl = new FileIngestServiceImpl();
  }

  @Test
  public void checkValidSampleFileNames() throws InvalidFileNameException {
    for (String filename : validSampleFileNames) {
      assertNotNull(fileIngestServiceImpl.verifyCSVFilename(filename, "sample"));
    }
  }

  @Test
  public void checkValidStaffFileNames() throws InvalidFileNameException {
    for (String filename : validStaffFileNames) {
      assertNotNull(fileIngestServiceImpl.verifyCSVFilename(filename, "staff"));
    }
  }

  @Test
  public void checkInvalidSampleFileNames() {
    for (String filename : invalidSampleFileNames) {
      try {
        assertNotNull(fileIngestServiceImpl.verifyCSVFilename(filename, "sample"));
        // we should throw an InvalidFileNameException before this point
        fail("False negative - filename '" + filename + "' should be invalid");
      } catch (InvalidFileNameException e) {
        // junk assertion - if we reach this point we have passed
        assertTrue(true);
      }
    }
  }

  @Test
  public void checkInvalidStaffFileNames() {
    for (String filename : invalidStaffFileNames) {
      try {
        assertNotNull(fileIngestServiceImpl.verifyCSVFilename(filename, "staff"));
        // we should throw an InvalidFileNameException before this point
        fail("False negative - filename '" + filename + "' should be invalid");
      } catch (InvalidFileNameException e) {
        // junk assertion - if we reach this point we have passed
        assertTrue(true);
      }
    }
  }
}

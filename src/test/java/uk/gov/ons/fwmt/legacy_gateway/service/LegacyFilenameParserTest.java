package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.service.FileIngestService;

import static org.junit.Assert.*;

public class LegacyFilenameParserTest {
  private CSVParsingService csvParsingServiceMock;
  private FileIngestService fileIngestService;

  public LegacyFilenameParserTest() {
    this.csvParsingServiceMock = Mockito.mock(CSVParsingService.class);
    this.fileIngestService = new FileIngestService(this.csvParsingServiceMock);
  }

  private final String[] validSampleFileNames = {
      "sample_GFF_2018-04-24T19:09:54Z.csv",
      "sample_LFS_2018-04-24T19:31:25Z.csv",
      "sample_LFS_2018-04-24T19-31-25Z.csv",
  };

  private final String[] validStaffFileNames = {
      "staff_2018-04-24T19:09:54Z.csv",
      "staff_2018-04-24T19:31:25Z.csv",
      "staff_2018-04-24T19-31-25Z.csv",
  };

  private final String[] invalidSampleFileNames = {
      "BLAH_BLAH-THIS_IS_WRONG",
      "sample_2018-04-24T19-31-25Z.csv",
  };

  private final String[] invalidStaffFileNames = {
      "BLAH_BLAH-THIS_IS_WRONG",
      "staff_2018-04-24R19-31-25Z.csv",
      "staff_EG_2018-04-24L19-31-25Z.csv",
      "foo_LFS_2018-04-24L19-31-25Z.csv",
      "staff_LFS_2018-04-24T19-31-25Z.csv",
  };

  @Test
  public void checkValidSampleFileNames() throws InvalidFileNameException {
    for (String filename : validSampleFileNames) {
      assertNotNull(fileIngestService.verifyCSVFilename(filename, "sample"));
    }
  }

  @Test
  public void checkValidStaffFileNames() throws InvalidFileNameException {
    for (String filename : validStaffFileNames) {
      assertNotNull(fileIngestService.verifyCSVFilename(filename, "staff"));
    }
  }

  @Test
  public void checkInvalidSampleFileNames() {
    for (String filename : invalidSampleFileNames) {
      try {
        assertNotNull(fileIngestService.verifyCSVFilename(filename, "sample"));
      } catch (InvalidFileNameException e) {
        continue;
      }
      fail("False negative - filename '" + filename + "' should be invalid");
    }
  }

  @Test
  public void checkInvalidStaffFileNames() {
    for (String filename : invalidStaffFileNames) {
      try {
        assertNotNull(fileIngestService.verifyCSVFilename(filename, "staff"));
      } catch (InvalidFileNameException e) {
        continue;
      }
      fail("False negative - filename '" + filename + "' should be invalid");
    }
  }
}

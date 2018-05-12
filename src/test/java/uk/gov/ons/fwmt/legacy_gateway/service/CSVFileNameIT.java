package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.Application;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.service.FileIngestService;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CSVFileNameIT {

  @Autowired
  private FileIngestService fileIngestService;

  @Test
  public void testValidCSVNames() throws InvalidFileNameException {
    try {
      fileIngestService.verifyCSVFilename("sample_LFS_2018-04-24T19:31:25Z.csv", "sample");
      fileIngestService.verifyCSVFilename("sample_LFS_2018-04-24T19-31-25Z.csv", "sample");
      fileIngestService.verifyCSVFilename("staff_2018-04-24T19:31:25Z.csv", "staff");
      fileIngestService.verifyCSVFilename("staff_2018-04-24T19-31-25Z.csv", "staff");
    } catch (InvalidFileNameException e) {
      fail("Valid file name was rejected");
    }
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames1() throws InvalidFileNameException {
    fileIngestService.verifyCSVFilename("staff_2018-04-24R19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames2() throws InvalidFileNameException {
    fileIngestService.verifyCSVFilename("staff_EG_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames3() throws InvalidFileNameException {
    fileIngestService.verifyCSVFilename("foo_LFS_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames4() throws InvalidFileNameException {
    fileIngestService.verifyCSVFilename("staff_LFS_2018-04-24T19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames5() throws InvalidFileNameException {
    fileIngestService.verifyCSVFilename("sample_2018-04-24T19-31-25Z.csv", "sample");
  }
}

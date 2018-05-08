package uk.gov.ons.fwmt.gateway.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.gateway.Application;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.utility.FileValidation;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CSVFileNameIT {
  @Autowired private FileValidation fileValidation;

  @Test
  public void testValidCSVNames() throws InvalidFileNameException {
    try {
      fileValidation.assertValidFilename("sample_LFS_2018-04-24T19:31:25Z.csv", "sample");
      fileValidation.assertValidFilename("sample_LFS_2018-04-24T19-31-25Z.csv", "sample");
      fileValidation.assertValidFilename("staff_2018-04-24T19:31:25Z.csv", "staff");
      fileValidation.assertValidFilename("staff_2018-04-24T19-31-25Z.csv", "staff");
    } catch (InvalidFileNameException e) {
      fail("Valid file name was rejected");
    }
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames1() throws InvalidFileNameException {
    fileValidation.assertValidFilename("staff_2018-04-24R19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames2() throws InvalidFileNameException {
    fileValidation.assertValidFilename("staff_EG_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames3() throws InvalidFileNameException {
    fileValidation.assertValidFilename("foo_LFS_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames4() throws InvalidFileNameException {
    fileValidation.assertValidFilename("staff_LFS_2018-04-24T19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames5() throws InvalidFileNameException {
    fileValidation.assertValidFilename("sample_2018-04-24T19-31-25Z.csv", "sample");
  }
}

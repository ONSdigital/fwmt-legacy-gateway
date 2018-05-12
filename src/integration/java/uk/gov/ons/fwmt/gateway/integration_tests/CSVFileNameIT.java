package uk.gov.ons.fwmt.gateway.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.gateway.Application;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.utility.LegacyFilename;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CSVFileNameIT {
  @Test
  public void testValidCSVNames() throws InvalidFileNameException {
    try {
      new LegacyFilename("sample_LFS_2018-04-24T19:31:25Z.csv", "sample");
      new LegacyFilename("sample_LFS_2018-04-24T19-31-25Z.csv", "sample");
      new LegacyFilename("staff_2018-04-24T19:31:25Z.csv", "staff");
      new LegacyFilename("staff_2018-04-24T19-31-25Z.csv", "staff");
    } catch (InvalidFileNameException e) {
      fail("Valid file name was rejected");
    }
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames1() throws InvalidFileNameException {
    new LegacyFilename("staff_2018-04-24R19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames2() throws InvalidFileNameException {
    new LegacyFilename("staff_EG_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames3() throws InvalidFileNameException {
    new LegacyFilename("foo_LFS_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames4() throws InvalidFileNameException {
    new LegacyFilename("staff_LFS_2018-04-24T19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames5() throws InvalidFileNameException {
    new LegacyFilename("sample_2018-04-24T19-31-25Z.csv", "sample");
  }
}

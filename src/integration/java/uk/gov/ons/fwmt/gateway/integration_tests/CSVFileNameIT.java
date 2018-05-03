package uk.gov.ons.fwmt.gateway.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.gateway.Application;
import uk.gov.ons.fwmt.gateway.controller.LegacyGatewayEndpoint;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class CSVFileNameIT {
  @Autowired LegacyGatewayEndpoint legacyGatewayEndpoint;

  @Test
  public void testValidCSVNames() throws InvalidFileNameException {
    legacyGatewayEndpoint.assertValidFilename("sample_LFS_2018-04-24T19:31:25Z.csv", "sample");
    legacyGatewayEndpoint.assertValidFilename("sample_LFS_2018-04-24T19-31-25Z.csv", "sample");
    legacyGatewayEndpoint.assertValidFilename("staff_LFS_2018-04-24T19:31:25Z.csv", "staff");
    legacyGatewayEndpoint.assertValidFilename("staff_LFS_2018-04-24T19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames1() throws InvalidFileNameException {
    legacyGatewayEndpoint.assertValidFilename("staff_LFS_2018-04-24R19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames2() throws InvalidFileNameException {
    legacyGatewayEndpoint.assertValidFilename("staff_EG_2018-04-24L19-31-25Z.csv", "staff");
  }

  @Test(expected = InvalidFileNameException.class)
  public void testInvalidCSVNames3() throws InvalidFileNameException {
    legacyGatewayEndpoint.assertValidFilename("foo_LFS_2018-04-24L19-31-25Z.csv", "staff");
  }
}

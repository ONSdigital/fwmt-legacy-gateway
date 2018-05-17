package uk.gov.ons.fwmt.legacy_gateway.trial;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.service.NewUserWebDrivenTest;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.TMWebDriver;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.impl.TMWebDriverImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UploadUsersTrialTest {
  private TMWebDriver tmWebDriver = new TMWebDriverImpl();
  @Test
  public void test() throws IOException, InterruptedException {
    File file = new File("/Users/ThomasPoot/Documents/work/fwmt-legacy-gateway/src/test/resources/sampledata/trial/10-05-18_TM_Pilot_Group_Users.csv");
    CSVParser parser = new CSVParser(new InputStreamReader(new FileInputStream(file)), CSVFormat.DEFAULT.withHeader());
    List<CSVRecord> records = parser.getRecords();
    for (CSVRecord record : records) {
      // make form
      UserForm form = new UserForm();
      form.setForename(record.get("Firstname"));
      form.setSurname(record.get("Lastname"));
      form.setUserName(record.get("Username"));
      form.setEmail(record.get("Email"));
      form.setJobTitle(record.get("JobTitle"));
      form.setTelNo(record.get("ContactNumber"));
      form.setIsApproved(true);
      form.setPasswordNeverExpires(true);
      form.setPassword("blank");
      System.out.println(form);

      // send the form
//      tmWebDriver.makeNewUser(form);

      // wait
      TimeUnit.SECONDS.sleep(1);
    }
  }
}

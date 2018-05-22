package uk.gov.ons.fwmt.legacy_gateway.trial;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.TMWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadUsersTrialTest {
  @Autowired
  private TMWebDriver tmWebDriver;

  @Autowired
  private TMUserRepo tmUserRepo;

  @Test
  @Ignore
  public void test() throws IOException, InterruptedException {
    File file = new File("src/test/resources/sampledata/trial/TM_Staff_Export_TH.csv");
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
      form.setPassword(record.get("Password"));
      System.out.println(form);

      TMUserEntity entity = new TMUserEntity();
      entity.setActive(false);
      entity.setAuthNo(record.get("Authno"));
      entity.setTmUsername(record.get("Username"));
      entity.setAlternateAuthNo(null);
//      tmUserRepo.save(entity);

      // send the form
//      tmWebDriver.makeNewUser(form);

      // wait
      TimeUnit.SECONDS.sleep(1);
    }
  }
}

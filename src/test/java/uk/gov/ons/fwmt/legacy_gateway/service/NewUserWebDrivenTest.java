package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.TMWebDriver;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.impl.TMWebDriverImpl;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TMWebDriver.class, TMWebDriverImpl.class})
public class NewUserWebDrivenTest {
  @Autowired
  private TMWebDriver TMWebDriver;

  @Test
  public void test() throws IOException {
    UserForm userForm = new UserForm();
    userForm.setUserName("Test");
    userForm.setEmail("Test@Test.com");
    userForm.setForename("Test");
    userForm.setSurname("Test");
    userForm.setJobTitle("Test");
    userForm.setPassword("Test@123");
    userForm.setTelNo("0");
    userForm.setIsApproved(true);
    userForm.setPasswordNeverExpires(true);
    TMWebDriver.makeNewUser(userForm);
  }
}

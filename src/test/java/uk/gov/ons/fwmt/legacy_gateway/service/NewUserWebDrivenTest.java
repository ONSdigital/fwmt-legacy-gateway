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
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TMWebDriver.class, TMWebDriverImpl.class})
public class NewUserWebDrivenTest {
  @Autowired
  private TMWebDriver TMWebDriver;

  @Test
  public void test() throws IOException {
    UserForm userForm1 = new UserForm();
    userForm1.setUserName("Test");
    userForm1.setEmail("Test@Test.com");
    userForm1.setForename("Test");
    userForm1.setSurname("Test");
    userForm1.setJobTitle("Test");
    userForm1.setPassword("Test@123");
    userForm1.setTelNo("0");
    userForm1.setIsApproved(true);
    userForm1.setPasswordNeverExpires(true);
    TMWebDriver.makeNewUser(userForm1);

    UserForm userForm2 = new UserForm();
    userForm2.setUserName("Test2");
    userForm2.setEmail("Test2@Test2.com");
    userForm2.setForename("Test2");
    userForm2.setSurname("Test");
    userForm2.setJobTitle("Test");
    userForm2.setPassword("Test2@123");
    userForm2.setTelNo("0");
    userForm2.setIsApproved(true);
    userForm2.setPasswordNeverExpires(true);
    TMWebDriver.makeNewUser(userForm2);
  }
}

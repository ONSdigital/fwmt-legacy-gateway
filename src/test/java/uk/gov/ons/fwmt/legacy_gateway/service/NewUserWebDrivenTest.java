package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.NewUserWebDriver;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.impl.NewUserWebDriverImpl;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NewUserWebDriver.class, NewUserWebDriverImpl.class})
public class NewUserWebDrivenTest {
  @Autowired
  private NewUserWebDriver newUserWebDriver;

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
    newUserWebDriver.makeNewUser(userForm);
  }
}

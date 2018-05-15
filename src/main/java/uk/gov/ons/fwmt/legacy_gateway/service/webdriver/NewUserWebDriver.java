package uk.gov.ons.fwmt.legacy_gateway.service.webdriver;

import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;

import java.io.IOException;
import java.util.List;

public interface NewUserWebDriver {
  void makeNewUser(UserForm userForm) throws IOException;
  void makeNewUsers(List<UserForm> userForms) throws IOException;
}

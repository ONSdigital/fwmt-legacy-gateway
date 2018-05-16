package uk.gov.ons.fwmt.legacy_gateway.service.webdriver.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.service.webdriver.TMWebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Service
public class TMWebDriverImpl implements TMWebDriver {
  private static int MAX_RETRIES = 1;

  @Value("${totalmobile.url}")
  private String baseUrl;

  @Value("${totalmobile.web.login-url}")
  private String loginUrl;

  @Value("${totalmobile.web.save-user-url}")
  private String saveUserUrl;

  @Value("${totalmobile.username}")
  private String username;

  @Value("${totalmobile.password}")
  private String password;

  // This class is a functional interface for actions which are retried
  private interface Action {
    boolean act() throws IOException;
  }

  // This class holds state about an ongoing set of web API calls
  private class Context {
    private Map<String, String> cookies;

    Context() throws IOException {
      login();
    }

    private void login() throws IOException {
      // Fetch the verification token and verification cookie
      String token;
      {
        Connection.Response response = Jsoup.connect(baseUrl + loginUrl).method(Connection.Method.GET).execute();

        // we're not logged in, so it's okay to destroy the cookies
        cookies = response.cookies();

        token = response.parse().selectFirst("form input[name=__RequestVerificationToken]").attr("value");

        // TODO verify success
      }

      // Use the verification token and cookie to log in
      {
        Connection.Response response = Jsoup.connect(baseUrl + loginUrl)
            .data(
                "Username", username,
                "Password", password,
                "ReturnUrl", "",
                "__RequestVerificationToken", token
            )
            .cookies(cookies)
            .method(Connection.Method.POST)
            .execute();

        cookies.putAll(response.cookies());

        // TODO verify success
      }
    }

    // Retry an operation several times
    // If we receive true, we assume that the method completed correctly
    // If we receive false, we assume that we must try to logon again
    public void retry(Action method) throws IOException {
      for (int i = 0; i <= MAX_RETRIES; i++) {
        if (method.act()) {
          return;
        } else {
          login();
        }
      }
    }

    public void makeNewUser(UserForm form) throws IOException {
      // Use the previously defined verification token and cookies to create a user
      retry(() -> {
        Connection.Response response = Jsoup.connect(baseUrl + saveUserUrl)
            .data(
                "Comment", "",
                "ProviderUserKey", "00000000-0000-0000-0000-000000000000",
                "UserName", form.getUserName(),
                "Password", form.getPassword(),
                "Password2", form.getPassword(),
                "Forename", form.getForename(),
                "Surname", form.getSurname(),
                "Email", form.getEmail(),
                "Email2", form.getEmail(),
                "ContactNo", form.getTelNo(),
                "JobTitle", form.getJobTitle(),
                "IsApproved", form.getIsApproved() ? "true" : "false",
                "PasswordNeverExpires", form.getPasswordNeverExpires() ? "true" : "false"
            )
            .ignoreContentType(true)
            .cookies(cookies)
            .method(Connection.Method.POST)
            .execute();

        Document document = response.parse();

        // TODO verify success

        return true;
      });
    }
  }

  @Override public void makeNewUser(UserForm userForm) throws IOException {
    Context context = new Context();
    context.makeNewUser(userForm);
  }

  // Prefer this method, it saves on processing by re-using the login context
  @Override public void makeNewUsers(List<UserForm> userForms) throws IOException {
    Context context = new Context();
    for (UserForm userForm : userForms) {
      context.makeNewUser(userForm);
    }
  }
}

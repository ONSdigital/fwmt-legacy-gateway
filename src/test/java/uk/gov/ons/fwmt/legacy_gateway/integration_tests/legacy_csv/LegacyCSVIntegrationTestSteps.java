package uk.gov.ons.fwmt.legacy_gateway.integration_tests.legacy_csv;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.fwmt.legacy_gateway.integration_tests.helpers.SpringIntegrationTestBase;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.TMService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class LegacyCSVIntegrationTestSteps extends SpringIntegrationTestBase {
  @Autowired
  private TMJobRepo tmJobRepo;

  @Autowired
  private TMUserRepo tmUserRepo;

  @Autowired
  private TMService tmService;

  @Given("^the database is clean$")
  public void theDatabaseIsClean() {
    tmJobRepo.deleteAll();
    tmUserRepo.deleteAll();
  }

  @When("^the CSV '([a-zA-Z0-9_-]+)' is uploaded to the '([a-zA-Z]+)' endpoint$")
  public void theCSVIsUploadedToTheEndpoint(String filename, String endpoint) {

  }

  @Then("^the database should contain (\\d+) jobs$")
  public void theDatabaseShouldContainJobs(int count) throws Throwable {
    assertEquals(count, tmJobRepo.count());
  }

  @Then("^the database should contain (\\d+) users$")
  public void theDatabaseShouldContainUsers(int count) throws Throwable {
    assertEquals(count, tmJobRepo.count());
  }

  @Then("^TM should be sent (\\d+) new jobs$")
  public void tmShouldBeSentNewJobs(int count) throws Throwable {
    ArgumentCaptor<CreateJobRequest> argumentCaptor = ArgumentCaptor.forClass(CreateJobRequest.class);
    verify(tmService).send(argumentCaptor.capture());
  }
}

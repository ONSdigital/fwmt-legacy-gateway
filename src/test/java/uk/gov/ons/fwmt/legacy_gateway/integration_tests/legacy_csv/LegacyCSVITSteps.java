package uk.gov.ons.fwmt.legacy_gateway.integration_tests.legacy_csv;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.fwmt.legacy_gateway.integration_tests.helpers.SpringITBase;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.impl.TMServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class LegacyCSVITSteps extends SpringITBase {
  @Autowired
  private TMJobRepo tmJobRepo;

  @Autowired
  private TMUserRepo tmUserRepo;

  @Autowired
  private TMServiceImpl tmServiceImpl;

  @Given("^the database is clean$")
  public void theDatabaseIsClean() {
    tmJobRepo.deleteAll();
    tmUserRepo.deleteAll();
  }

  @When("^the CSV '([a-zA-Z0-9_-]+)' is uploaded to the '([a-zA-Z]+)' endpoint$")
  public void theCSVIsUploadedToTheEndpoint(String filename, String endpoint) {
    throw new PendingException();
  }

  @When("^the CSV '([a-zA-Z0-9_-]+)' is imported from sample '([a-zA-Z0-9_-]+)' and uploaded to the '([a-zA-Z]+)' endpoint$")
  public void theCSVSample_LFS_TZIsImportedFromSampleSampleLFSExampleAllRowsCsvAndUploadedToTheSampleEndpoint(String csvName, String originalCsvName, String endpoint) throws Throwable {
    throw new PendingException();
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
    verify(tmServiceImpl).send(argumentCaptor.capture());
  }

}

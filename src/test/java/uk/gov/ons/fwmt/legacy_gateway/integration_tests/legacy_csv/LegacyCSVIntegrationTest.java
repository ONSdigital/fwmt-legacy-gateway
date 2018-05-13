package uk.gov.ons.fwmt.legacy_gateway.integration_tests.legacy_csv;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/cucumber/LegacyCSV.feature"},
    glue = {"uk.gov.ons.fwmt.legacy_gateway.integration_tests.legacy_csv"})
public class LegacyCSVIntegrationTest {
}

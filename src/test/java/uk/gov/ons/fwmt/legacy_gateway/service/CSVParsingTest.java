package uk.gov.ons.fwmt.legacy_gateway.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.legacy_gateway.service.impl.CSVParsingServiceImpl;
import uk.gov.ons.fwmt.legacy_gateway.service.impl.LegacyJobPublishServiceImpl;
import uk.gov.ons.fwmt.legacy_gateway.service.impl.LegacyStaffPublishServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class CSVParsingTest {
  private CSVParsingServiceImpl csvParsingServiceImpl;

  @Mock
  private LegacyJobPublishServiceImpl legacyJobPublishServiceImpl;

  @Mock
  private LegacyStaffPublishServiceImpl legacyStaffPublishServiceImpl;

  @Captor
  private ArgumentCaptor<List<LegacyStaffIngest>> legacyStaffArgumentCaptor;

  @Captor
  private ArgumentCaptor<LegacySampleIngest> legacySampleArgumentCaptor;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    this.csvParsingServiceImpl = new CSVParsingServiceImpl(this.legacyStaffPublishServiceImpl, this.legacyJobPublishServiceImpl);
  }

  @Test
  @Ignore
  public void parseStaffCSV() throws Exception {
    File testFile = new File("src/test/resources/sampledata/public/staffExampleAllRows.csv");

    try (FileInputStream testFileInputStream = new FileInputStream(testFile)) {
      csvParsingServiceImpl.parseLegacyStaff(new InputStreamReader(testFileInputStream));
    }

    verify(legacyStaffPublishServiceImpl).publishStaff(legacyStaffArgumentCaptor.capture());

    assertEquals(1, legacyStaffArgumentCaptor.getValue().size());

//    LegacyStaffIngest staffIngest = legacyStaffArgumentCaptor.getValue().get(0);
  }

  @Test
  @Ignore
  public void parseGFFSampleCSV() throws IOException {
    File testFile = new File("src/test/resources/sampledata/public/sampleGFFExampleAllRows.csv");

    try (FileInputStream testFileInputStream = new FileInputStream(testFile)) {
      csvParsingServiceImpl.parseLegacySample(new InputStreamReader(testFileInputStream), LegacySampleSurveyType.GFF);
    }

    verify(legacyJobPublishServiceImpl).publishJob(legacySampleArgumentCaptor.capture());

    LegacySampleIngest sampleIngest = legacySampleArgumentCaptor.getValue();

    assertEquals("2018-05-04 11:34:34.753000000", sampleIngest.getTimestamp());
    assertEquals("1630615 1", sampleIngest.getSerNo());
    assertEquals("NSW", sampleIngest.getTla());
    assertEquals("2018", sampleIngest.getYear());
    assertEquals("01", sampleIngest.getMonth());
    assertEquals("801", sampleIngest.getStage());
    assertEquals("1", sampleIngest.getWave());
    assertEquals("19 LLEWELLYN DRIVE", sampleIngest.getAddressLine1());
    assertEquals("", sampleIngest.getAddressLine2());
    assertEquals("", sampleIngest.getAddressLine3());
    assertEquals("", sampleIngest.getAddressLine4());
    assertEquals("", sampleIngest.getDistrict());
    assertEquals("CAERPHILLY", sampleIngest.getPostTown());
    assertEquals("CF833FU", sampleIngest.getPostcode());
    assertEquals("16306", sampleIngest.getQuota());
    assertEquals("015", sampleIngest.getAddressNo());
    assertEquals("31506,87884", sampleIngest.getOsGridRef());
    assertEquals("1", sampleIngest.getIssueNo());
    assertEquals("1", sampleIngest.getPart());
    assertEquals("6310", sampleIngest.getAuth());
    assertEquals("996310", sampleIngest.getEmployeeNo());
    assertEquals("2018-03-27 16:23:00.633000000", sampleIngest.getLastUpdated());
    assertEquals("", sampleIngest.getGffData().getName());
    assertEquals(" ", sampleIngest.getGffData().getRand());
    assertEquals("W06000018", sampleIngest.getGffData().getLaua());
    assertEquals("Caerphilly", sampleIngest.getGffData().getLauaName());
//    assertEquals("W05000923", sampleIngest.getGffData().getWard());
//    assertEquals("Morgan Jones", sampleIngest.getGffData().getWardName());
//    assertEquals("", sampleIngest.getGffData().getMO());
//    assertEquals("0", sampleIngest.getGffData().getDivAddInd());
//    assertEquals("1", sampleIngest.getGffData().getGffmu());
    assertEquals(" ", sampleIngest.getGffData().getOldSerial());
    assertEquals("0", sampleIngest.getGffData().getSubSample());
    assertEquals("2", sampleIngest.getGffData().getAdult2());
    assertEquals("3", sampleIngest.getGffData().getAdult3());
    assertEquals("3", sampleIngest.getGffData().getAdult4());
    assertEquals("5", sampleIngest.getGffData().getAdult5());
    assertEquals("5", sampleIngest.getGffData().getAdult6());
    assertEquals("2", sampleIngest.getGffData().getAdult7());
    assertEquals("8", sampleIngest.getGffData().getAdult8());
    assertEquals("3", sampleIngest.getGffData().getAdult9());
    assertEquals("7", sampleIngest.getGffData().getAdult10());
    assertEquals("6", sampleIngest.getGffData().getAdult11());
    assertEquals("5", sampleIngest.getGffData().getAdult12());
    assertEquals("10", sampleIngest.getGffData().getAdult13());
    assertEquals("14", sampleIngest.getGffData().getAdult14());
  }

  @Test
  @Ignore
  public void parseLFSSampleCSV() throws IOException {
    File testFile = new File("src/test/resources/sampledata/public/sampleLFSExampleAllRows.csv");

    try (FileInputStream testFileInputStream = new FileInputStream(testFile)) {
      csvParsingServiceImpl.parseLegacySample(new InputStreamReader(testFileInputStream), LegacySampleSurveyType.LFS);
    }

    verify(legacyJobPublishServiceImpl).publishJob(legacySampleArgumentCaptor.capture());

//    LegacySampleIngest sampleIngest = legacySampleArgumentCaptor.getValue();
  }


}

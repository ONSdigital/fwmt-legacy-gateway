package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;

import java.io.*;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CSVParsingTest {
  private CSVParsingServiceImpl csvParsingServiceImpl = new CSVParsingServiceImpl();

  @Test
  @Ignore
  public void parseStaffCSV() throws Exception {
    File testFile = new File("src/test/resources/sampledata/public/staffExampleAllRows.csv");

    try (FileInputStream testFileInputStream = new FileInputStream(testFile)) {
      Iterator<CSVParseResult<LegacyStaffIngest>> iterator = csvParsingServiceImpl
          .parseLegacyStaff(new InputStreamReader(testFileInputStream));

      CSVParseResult<LegacyStaffIngest> result = iterator.next();
      assertTrue(result.isResult());
    }

  }

  @Test
  public void parseGFFSampleCSV() throws IOException {
    File testFile = new File("src/test/resources/sampledata/unit_tests/sample_GFF_2018-05-17T15-34-00Z.csv");
    Reader reader = new InputStreamReader(new FileInputStream(testFile));
    Iterator<CSVParseResult<LegacySampleIngest>> iterator = csvParsingServiceImpl
        .parseLegacySample(reader, LegacySampleSurveyType.GFF);

    // retrieve first row
    CSVParseResult<LegacySampleIngest> result1 = iterator.next();
    assertTrue(result1.isResult());
    // retrieve second row
    CSVParseResult<LegacySampleIngest> result2 = iterator.next();
    assertTrue(result2.isResult());
    // assert that we have no more results
    assertFalse(iterator.hasNext());
    LegacySampleIngest sampleIngest1 = result1.getResult();
    LegacySampleIngest sampleIngest2 = result2.getResult();

    assertEquals("transmission_date_1", sampleIngest1.getTimestamp());
    assertEquals("serno_1", sampleIngest1.getSerNo());
    assertEquals("tla_1", sampleIngest1.getTla());
    assertEquals("year_1", sampleIngest1.getYear());
    assertEquals("month_1", sampleIngest1.getMonth());
    assertEquals("801", sampleIngest1.getStage());
    assertEquals("wave_1", sampleIngest1.getWave());
    assertEquals("name_1", sampleIngest1.getGffData().getName());
    assertEquals("prem1_1", sampleIngest1.getAddressLine1());
    assertEquals("prem2_1", sampleIngest1.getAddressLine2());
    assertEquals("prem3_1", sampleIngest1.getAddressLine3());
    assertEquals("prem4_1", sampleIngest1.getAddressLine4());
    assertEquals("district_1", sampleIngest1.getDistrict());
    assertEquals("posttown_1", sampleIngest1.getPostTown());
    assertEquals("postcode_1", sampleIngest1.getPostcode());
    assertEquals("quota_1", sampleIngest1.getQuota());
    assertEquals("addressno_1", sampleIngest1.getAddressNo());
    assertEquals("12345,67890", sampleIngest1.getOsGridRef());
    assertEquals("issue_no_1", sampleIngest1.getIssueNo());
    assertEquals("part_1", sampleIngest1.getPart());
    assertEquals("auth_1", sampleIngest1.getAuth());
    assertEquals("employeeno_1", sampleIngest1.getEmployeeNo());
    assertEquals("last_updated_1", sampleIngest1.getLastUpdated());
    assertEquals("rand_1", sampleIngest1.getGffData().getRand());
    // Ignored fields
    //    assertEquals("laua_1", sampleIngest1.getGffData().getLaua());
    //    assertEquals("laua_name_1", sampleIngest1.getGffData().getLauaName());
    //    assertEquals("ward_1", sampleIngest1.getGffData().getWard());
    //    assertEquals("ward_name_1", sampleIngest1.getGffData().getWardName());
    //    assertEquals("mo_1", sampleIngest1.getGffData().getMo());
    //    assertEquals("divaddind_1", sampleIngest1.getGffData().getDivAddInd());
    //    assertEquals("gffmu_1", sampleIngest1.getGffData().getGffmu());
    assertEquals("oldserial_1", sampleIngest1.getGffData().getOldSerial());
    assertEquals("subsample_1", sampleIngest1.getGffData().getSubSample());
    assertEquals("adult2_1", sampleIngest1.getGffData().getAdult2());
    assertEquals("adult3_1", sampleIngest1.getGffData().getAdult3());
    assertEquals("adult4_1", sampleIngest1.getGffData().getAdult4());
    assertEquals("adult5_1", sampleIngest1.getGffData().getAdult5());
    assertEquals("adult6_1", sampleIngest1.getGffData().getAdult6());
    assertEquals("adult7_1", sampleIngest1.getGffData().getAdult7());
    assertEquals("adult8_1", sampleIngest1.getGffData().getAdult8());
    assertEquals("adult9_1", sampleIngest1.getGffData().getAdult9());
    assertEquals("adult10_1", sampleIngest1.getGffData().getAdult10());
    assertEquals("adult11_1", sampleIngest1.getGffData().getAdult11());
    assertEquals("adult12_1", sampleIngest1.getGffData().getAdult12());
    assertEquals("adult13_1", sampleIngest1.getGffData().getAdult13());
    assertEquals("adult14_1", sampleIngest1.getGffData().getAdult14());

    assertEquals("transmission_date_2", sampleIngest2.getTimestamp());
    assertEquals("serno_2", sampleIngest2.getSerNo());
    assertEquals("tla_2", sampleIngest2.getTla());
    assertEquals("year_2", sampleIngest2.getYear());
    assertEquals("month_2", sampleIngest2.getMonth());
    assertEquals("801", sampleIngest2.getStage());
    assertEquals("wave_2", sampleIngest2.getWave());
    assertEquals("name_2", sampleIngest2.getGffData().getName());
    assertEquals("prem1_2", sampleIngest2.getAddressLine1());
    assertEquals("prem2_2", sampleIngest2.getAddressLine2());
    assertEquals("prem3_2", sampleIngest2.getAddressLine3());
    assertEquals("prem4_2", sampleIngest2.getAddressLine4());
    assertEquals("district_2", sampleIngest2.getDistrict());
    assertEquals("posttown_2", sampleIngest2.getPostTown());
    assertEquals("postcode_2", sampleIngest2.getPostcode());
    assertEquals("quota_2", sampleIngest2.getQuota());
    assertEquals("addressno_2", sampleIngest2.getAddressNo());
    assertEquals("09876,54321", sampleIngest2.getOsGridRef());
    assertEquals("issue_no_2", sampleIngest2.getIssueNo());
    assertEquals("part_2", sampleIngest2.getPart());
    assertEquals("auth_2", sampleIngest2.getAuth());
    assertEquals("employeeno_2", sampleIngest2.getEmployeeNo());
    assertEquals("last_updated_2", sampleIngest2.getLastUpdated());
    assertEquals("rand_2", sampleIngest2.getGffData().getRand());
    // Ignored fields
    //    assertEquals("laua_2", sampleIngest2.getGffData().getLaua());
    //    assertEquals("laua_name_2", sampleIngest2.getGffData().getLauaName());
    //    assertEquals("ward_2", sampleIngest2.getGffData().getWard());
    //    assertEquals("ward_name_2", sampleIngest2.getGffData().getWardName());
    //    assertEquals("mo_2", sampleIngest2.getGffData().getMo());
    //    assertEquals("divaddind_2", sampleIngest2.getGffData().getDivAddInd());
    //    assertEquals("gffmu_2", sampleIngest2.getGffData().getGffmu());
    assertEquals("oldserial_2", sampleIngest2.getGffData().getOldSerial());
    assertEquals("subsample_2", sampleIngest2.getGffData().getSubSample());
    assertEquals("adult2_2", sampleIngest2.getGffData().getAdult2());
    assertEquals("adult3_2", sampleIngest2.getGffData().getAdult3());
    assertEquals("adult4_2", sampleIngest2.getGffData().getAdult4());
    assertEquals("adult5_2", sampleIngest2.getGffData().getAdult5());
    assertEquals("adult6_2", sampleIngest2.getGffData().getAdult6());
    assertEquals("adult7_2", sampleIngest2.getGffData().getAdult7());
    assertEquals("adult8_2", sampleIngest2.getGffData().getAdult8());
    assertEquals("adult9_2", sampleIngest2.getGffData().getAdult9());
    assertEquals("adult10_2", sampleIngest2.getGffData().getAdult10());
    assertEquals("adult11_2", sampleIngest2.getGffData().getAdult11());
    assertEquals("adult12_2", sampleIngest2.getGffData().getAdult12());
    assertEquals("adult13_2", sampleIngest2.getGffData().getAdult13());
    assertEquals("adult14_2", sampleIngest2.getGffData().getAdult14());
  }

  @Test
  @Ignore
  public void parseLFSSampleCSV() throws IOException {
    File testFile = new File("src/test/resources/sampledata/unit_tests/sample_LFS_2018-05-17T15-34-00Z.csv");
    Reader reader = new InputStreamReader(new FileInputStream(testFile));
    Iterator<CSVParseResult<LegacySampleIngest>> iterator = csvParsingServiceImpl
        .parseLegacySample(reader, LegacySampleSurveyType.LFS);

    // retrieve first row
    CSVParseResult<LegacySampleIngest> result1 = iterator.next();
    assertTrue(result1.isResult());
    // retrieve second row
    CSVParseResult<LegacySampleIngest> result2 = iterator.next();
    assertTrue(result2.isResult());
    // assert that we have no more results
    assertFalse(iterator.hasNext());
    LegacySampleIngest sampleIngest1 = result1.getResult();
    LegacySampleIngest sampleIngest2 = result2.getResult();

    assertEquals("transmission_date_1", sampleIngest1.getTimestamp());
    assertEquals("serno_1", sampleIngest1.getSerNo());
    assertEquals("tla_1", sampleIngest1.getTla());
    assertEquals("81K", sampleIngest1.getStage());
    assertEquals("issue_no_1", sampleIngest1.getIssueNo());
    assertEquals("part_1", sampleIngest1.getPart());
    assertEquals("auth_1", sampleIngest1.getAuth());
    assertEquals("employeeno_1", sampleIngest1.getEmployeeNo());
    assertEquals("last_updated_1", sampleIngest1.getLastUpdated());
    assertEquals("quota_1", sampleIngest1.getQuota());
    assertEquals("addr_1", sampleIngest1.getAddressNo());
    assertEquals("thiswv_1", sampleIngest1.getWave());
    assertEquals("prem1_1", sampleIngest1.getAddressLine1());
    assertEquals("prem2_1", sampleIngest1.getAddressLine2());
    assertEquals("prem3_1", sampleIngest1.getAddressLine3());
    assertEquals("prem4_1", sampleIngest1.getAddressLine4());
    assertEquals("district_1", sampleIngest1.getDistrict());
    assertEquals("posttown_1", sampleIngest1.getPostTown());
    assertEquals("postcode_1", sampleIngest1.getPostcode());
    assertEquals("teleno_1", sampleIngest1.getTelNo());
    assertEquals("12345,67890", sampleIngest1.getOsGridRef());
    assertEquals("refdate_1", sampleIngest1.getLfsData().getRefDate());
    assertEquals("direction_1", sampleIngest1.getLfsData().getDirection());
    assertEquals("lstho_1", sampleIngest1.getLfsData().getLstho());
    assertEquals("main_1", sampleIngest1.getLfsData().getMain());
    assertEquals("numhhld_1", sampleIngest1.getLfsData().getNumberHouseholds());
    assertEquals("hhlddesc_1", sampleIngest1.getLfsData().getHouseholdsDesc());
    assertEquals("qres_line_name_1_1", sampleIngest1.getLfsData().getRespondentName1());
    assertEquals("qres_line_name_2_1", sampleIngest1.getLfsData().getRespondentName2());
    assertEquals("qres_line_name_3_1", sampleIngest1.getLfsData().getRespondentName3());
    assertEquals("qres_line_name_4_1", sampleIngest1.getLfsData().getRespondentName4());
    assertEquals("qres_line_name_5_1", sampleIngest1.getLfsData().getRespondentName5());
    assertEquals("qres_line_name_6_1", sampleIngest1.getLfsData().getRespondentName6());
    assertEquals("qres_line_name_7_1", sampleIngest1.getLfsData().getRespondentName7());
    assertEquals("qres_line_name_8_1", sampleIngest1.getLfsData().getRespondentName8());
    assertEquals("qres_line_name_9_1", sampleIngest1.getLfsData().getRespondentName9());
    assertEquals("qres_line_name_10_1", sampleIngest1.getLfsData().getRespondentName10());
    assertEquals("qres_line_name_11_1", sampleIngest1.getLfsData().getRespondentName11());
    assertEquals("qres_line_name_12_1", sampleIngest1.getLfsData().getRespondentName12());
    assertEquals("qres_line_name_13_1", sampleIngest1.getLfsData().getRespondentName13());
    assertEquals("qres_line_name_14_1", sampleIngest1.getLfsData().getRespondentName14());
    assertEquals("qres_line_name_15_1", sampleIngest1.getLfsData().getRespondentName15());
    assertEquals("qres_line_name_16_1", sampleIngest1.getLfsData().getRespondentName16());
    assertEquals("qres_line_age_1_1", sampleIngest1.getLfsData().getRespondentAge1());
    assertEquals("qres_line_age_2_1", sampleIngest1.getLfsData().getRespondentAge2());
    assertEquals("qres_line_age_3_1", sampleIngest1.getLfsData().getRespondentAge3());
    assertEquals("qres_line_age_4_1", sampleIngest1.getLfsData().getRespondentAge4());
    assertEquals("qres_line_age_5_1", sampleIngest1.getLfsData().getRespondentAge5());
    assertEquals("qres_line_age_6_1", sampleIngest1.getLfsData().getRespondentAge6());
    assertEquals("qres_line_age_7_1", sampleIngest1.getLfsData().getRespondentAge7());
    assertEquals("qres_line_age_8_1", sampleIngest1.getLfsData().getRespondentAge8());
    assertEquals("qres_line_age_9_1", sampleIngest1.getLfsData().getRespondentAge9());
    assertEquals("qres_line_age_10_1", sampleIngest1.getLfsData().getRespondentAge10());
    assertEquals("qres_line_age_11_1", sampleIngest1.getLfsData().getRespondentAge11());
    assertEquals("qres_line_age_12_1", sampleIngest1.getLfsData().getRespondentAge12());
    assertEquals("qres_line_age_13_1", sampleIngest1.getLfsData().getRespondentAge13());
    assertEquals("qres_line_age_14_1", sampleIngest1.getLfsData().getRespondentAge14());
    assertEquals("qres_line_age_15_1", sampleIngest1.getLfsData().getRespondentAge15());
    assertEquals("qres_line_age_16_1", sampleIngest1.getLfsData().getRespondentAge16());
    assertEquals("qindiv_1_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator1());
    assertEquals("qindiv_1_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork1());
    assertEquals("qindiv_1_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType1());
    assertEquals("qindiv_2_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator2());
    assertEquals("qindiv_2_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork2());
    assertEquals("qindiv_2_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType2());
    assertEquals("qindiv_3_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator3());
    assertEquals("qindiv_3_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork3());
    assertEquals("qindiv_3_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType3());
    assertEquals("qindiv_4_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator4());
    assertEquals("qindiv_4_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork4());
    assertEquals("qindiv_4_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType4());
    assertEquals("qindiv_5_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator5());
    assertEquals("qindiv_5_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork5());
    assertEquals("qindiv_5_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType5());
    assertEquals("qindiv_6_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator6());
    assertEquals("qindiv_6_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork6());
    assertEquals("qindiv_6_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType6());
    assertEquals("qindiv_7_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator7());
    assertEquals("qindiv_7_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork7());
    assertEquals("qindiv_7_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType7());
    assertEquals("qindiv_8_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator8());
    assertEquals("qindiv_8_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork8());
    assertEquals("qindiv_8_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType8());
    assertEquals("qindiv_9_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator9());
    assertEquals("qindiv_9_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork9());
    assertEquals("qindiv_9_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType9());
    assertEquals("qindiv_10_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator10());
    assertEquals("qindiv_10_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork10());
    assertEquals("qindiv_10_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType10());
    assertEquals("qindiv_11_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator11());
    assertEquals("qindiv_11_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork11());
    assertEquals("qindiv_11_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType11());
    assertEquals("qindiv_12_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator12());
    assertEquals("qindiv_12_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork12());
    assertEquals("qindiv_12_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType12());
    assertEquals("qindiv_13_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator13());
    assertEquals("qindiv_13_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork13());
    assertEquals("qindiv_13_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType13());
    assertEquals("qindiv_14_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator14());
    assertEquals("qindiv_14_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork14());
    assertEquals("qindiv_14_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType14());
    assertEquals("qindiv_15_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator15());
    assertEquals("qindiv_15_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork15());
    assertEquals("qindiv_15_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType15());
    assertEquals("qindiv_16_wrking_1", sampleIngest1.getLfsData().getRespondentWorkIndicator16());
    assertEquals("qindiv_16_look4_1", sampleIngest1.getLfsData().getRespondentLookingForWork16());
    assertEquals("qindiv_16_indout_1", sampleIngest1.getLfsData().getRespondentInterviewType16());
    assertEquals("comment_1_intvno_1", sampleIngest1.getLfsData().getComment1InterviewerNo());
    assertEquals("comment_1_brief1_1", sampleIngest1.getLfsData().getComment1BriefNotes1());
    assertEquals("comment_2_intvno_1", sampleIngest1.getLfsData().getComment2InterviewerNo());
    assertEquals("comment_2_brief1_1", sampleIngest1.getLfsData().getComment2BriefNotes1());
    assertEquals("comment_3_intvno_1", sampleIngest1.getLfsData().getComment3InterviewerNo());
    assertEquals("comment_3_brief1_1", sampleIngest1.getLfsData().getComment3BriefNotes1());
    assertEquals("comment_4_intvno_1", sampleIngest1.getLfsData().getComment4InterviewerNo());
    assertEquals("comment_4_brief1_1", sampleIngest1.getLfsData().getComment4BriefNotes1());
    assertEquals("comment_5_intvno_1", sampleIngest1.getLfsData().getComment5InterviewerNo());
    assertEquals("comment_5_brief1_1", sampleIngest1.getLfsData().getComment5BriefNotes1());
    // ignored
    //    assertEquals("quota_no_1", sampleIngest1.getLfsData().getQuotaNo());
    //    assertEquals("week_1", sampleIngest1.getLfsData().getWeek());
    //    assertEquals("w1yr_1", sampleIngest1.getLfsData().getW1yr());
    //    assertEquals("qrtr_1", sampleIngest1.getLfsData().getQrtr());
    //    assertEquals("wavfnd_1", sampleIngest1.getLfsData().getWavfnd());
    //    assertEquals("hhld_1", sampleIngest1.getLfsData().getHhld());
    //    assertEquals("chklet_1", sampleIngest1.getLfsData().getChklet());
    //    assertEquals("divaddind_1", sampleIngest1.getLfsData().getDivAddInd());
    //    assertEquals("mo_1", sampleIngest1.getLfsData().getMo());
    //    assertEquals("hout_1", sampleIngest1.getLfsData().getHOut());
    //    assertEquals("lfssamp_1", sampleIngest1.getLfsData().getLfsSamp());
    //    assertEquals("thanks_1", sampleIngest1.getLfsData().getThanks());
    //    assertEquals("thanke_1", sampleIngest1.getLfsData().getThanke());
    //    assertEquals("recphone_1", sampleIngest1.getLfsData().getRecPhone());
    //    assertEquals("country_1", sampleIngest1.getLfsData().getCountry());
    //    assertEquals("numper_1", sampleIngest1.getLfsData().getNumper());
    //    assertEquals("qindiv_1_jbaway_1", sampleIngest1.getLfsData().getQindiv1Jbaway());
    //    assertEquals("qindiv_1_ownbus_1", sampleIngest1.getLfsData().getQindiv1Ownbus());
    //    assertEquals("qindiv_1_relbus_1", sampleIngest1.getLfsData().getQindiv1Relbus());
    //    assertEquals("qindiv_1_difjob_1", sampleIngest1.getLfsData().getQindiv1Difjob());
    //    assertEquals("qindiv_2_jbaway_1", sampleIngest1.getLfsData().getQindiv2Jbaway());
    //    assertEquals("qindiv_2_ownbus_1", sampleIngest1.getLfsData().getQindiv2Ownbus());
    //    assertEquals("qindiv_2_relbus_1", sampleIngest1.getLfsData().getQindiv2Relbus());
    //    assertEquals("qindiv_2_difjob_1", sampleIngest1.getLfsData().getQindiv2Difjob());
    //    assertEquals("qindiv_3_jbaway_1", sampleIngest1.getLfsData().getQindiv3Jbaway());
    //    assertEquals("qindiv_3_ownbus_1", sampleIngest1.getLfsData().getQindiv3Ownbus());
    //    assertEquals("qindiv_3_relbus_1", sampleIngest1.getLfsData().getQindiv3Relbus());
    //    assertEquals("qindiv_3_difjob_1", sampleIngest1.getLfsData().getQindiv3Difjob());
    //    assertEquals("qindiv_4_jbaway_1", sampleIngest1.getLfsData().getQindiv4Jbaway());
    //    assertEquals("qindiv_4_ownbus_1", sampleIngest1.getLfsData().getQindiv4Ownbus());
    //    assertEquals("qindiv_4_relbus_1", sampleIngest1.getLfsData().getQindiv4Relbus());
    //    assertEquals("qindiv_4_difjob_1", sampleIngest1.getLfsData().getQindiv4Difjob());
    //    assertEquals("qindiv_5_jbaway_1", sampleIngest1.getLfsData().getQindiv5Jbaway());
    //    assertEquals("qindiv_5_ownbus_1", sampleIngest1.getLfsData().getQindiv5Ownbus());
    //    assertEquals("qindiv_5_relbus_1", sampleIngest1.getLfsData().getQindiv5Relbus());
    //    assertEquals("qindiv_5_difjob_1", sampleIngest1.getLfsData().getQindiv5Difjob());
    //    assertEquals("qindiv_6_jbaway_1", sampleIngest1.getLfsData().getQindiv6Jbaway());
    //    assertEquals("qindiv_6_ownbus_1", sampleIngest1.getLfsData().getQindiv6Ownbus());
    //    assertEquals("qindiv_6_relbus_1", sampleIngest1.getLfsData().getQindiv6Relbus());
    //    assertEquals("qindiv_6_difjob_1", sampleIngest1.getLfsData().getQindiv6Difjob());
    //    assertEquals("qindiv_7_jbaway_1", sampleIngest1.getLfsData().getQindiv7Jbaway());
    //    assertEquals("qindiv_7_ownbus_1", sampleIngest1.getLfsData().getQindiv7Ownbus());
    //    assertEquals("qindiv_7_relbus_1", sampleIngest1.getLfsData().getQindiv7Relbus());
    //    assertEquals("qindiv_7_difjob_1", sampleIngest1.getLfsData().getQindiv7Difjob());
    //    assertEquals("qindiv_8_jbaway_1", sampleIngest1.getLfsData().getQindiv8Jbaway());
    //    assertEquals("qindiv_8_ownbus_1", sampleIngest1.getLfsData().getQindiv8Ownbus());
    //    assertEquals("qindiv_8_relbus_1", sampleIngest1.getLfsData().getQindiv8Relbus());
    //    assertEquals("qindiv_8_difjob_1", sampleIngest1.getLfsData().getQindiv8Difjob());
    //    assertEquals("qindiv_9_jbaway_1", sampleIngest1.getLfsData().getQindiv9Jbaway());
    //    assertEquals("qindiv_9_ownbus_1", sampleIngest1.getLfsData().getQindiv9Ownbus());
    //    assertEquals("qindiv_9_relbus_1", sampleIngest1.getLfsData().getQindiv9Relbus());
    //    assertEquals("qindiv_9_difjob_1", sampleIngest1.getLfsData().getQindiv9Difjob());
    //    assertEquals("qindiv_10_jbaway_1", sampleIngest1.getLfsData().getQindiv10Jbaway());
    //    assertEquals("qindiv_10_ownbus_1", sampleIngest1.getLfsData().getQindiv10Ownbus());
    //    assertEquals("qindiv_10_relbus_1", sampleIngest1.getLfsData().getQindiv10Relbus());
    //    assertEquals("qindiv_10_difjob_1", sampleIngest1.getLfsData().getQindiv10Difjob());
    //    assertEquals("qindiv_11_jbaway_1", sampleIngest1.getLfsData().getQindiv11Jbaway());
    //    assertEquals("qindiv_11_ownbus_1", sampleIngest1.getLfsData().getQindiv11Ownbus());
    //    assertEquals("qindiv_11_relbus_1", sampleIngest1.getLfsData().getQindiv11Relbus());
    //    assertEquals("qindiv_11_difjob_1", sampleIngest1.getLfsData().getQindiv11Difjob());
    //    assertEquals("qindiv_12_jbaway_1", sampleIngest1.getLfsData().getQindiv12Jbaway());
    //    assertEquals("qindiv_12_ownbus_1", sampleIngest1.getLfsData().getQindiv12Ownbus());
    //    assertEquals("qindiv_12_relbus_1", sampleIngest1.getLfsData().getQindiv12Relbus());
    //    assertEquals("qindiv_12_difjob_1", sampleIngest1.getLfsData().getQindiv12Difjob());
    //    assertEquals("qindiv_13_jbaway_1", sampleIngest1.getLfsData().getQindiv13Jbaway());
    //    assertEquals("qindiv_13_ownbus_1", sampleIngest1.getLfsData().getQindiv13Ownbus());
    //    assertEquals("qindiv_13_relbus_1", sampleIngest1.getLfsData().getQindiv13Relbus());
    //    assertEquals("qindiv_13_difjob_1", sampleIngest1.getLfsData().getQindiv13Difjob());
    //    assertEquals("qindiv_14_jbaway_1", sampleIngest1.getLfsData().getQindiv14Jbaway());
    //    assertEquals("qindiv_14_ownbus_1", sampleIngest1.getLfsData().getQindiv14Ownbus());
    //    assertEquals("qindiv_14_relbus_1", sampleIngest1.getLfsData().getQindiv14Relbus());
    //    assertEquals("qindiv_14_difjob_1", sampleIngest1.getLfsData().getQindiv14Difjob());
    //    assertEquals("qindiv_15_jbaway_1", sampleIngest1.getLfsData().getQindiv15Jbaway());
    //    assertEquals("qindiv_15_ownbus_1", sampleIngest1.getLfsData().getQindiv15Ownbus());
    //    assertEquals("qindiv_15_relbus_1", sampleIngest1.getLfsData().getQindiv15Relbus());
    //    assertEquals("qindiv_15_difjob_1", sampleIngest1.getLfsData().getQindiv15Difjob());
    //    assertEquals("qindiv_16_jbaway_1", sampleIngest1.getLfsData().getQindiv16Jbaway());
    //    assertEquals("qindiv_16_ownbus_1", sampleIngest1.getLfsData().getQindiv16Ownbus());
    //    assertEquals("qindiv_16_relbus_1", sampleIngest1.getLfsData().getQindiv16Relbus());
    //    assertEquals("qindiv_16_difjob_1", sampleIngest1.getLfsData().getQindiv16Difjob());
    //    assertEquals("comment_1_refdte_1", sampleIngest1.getLfsData().getComment1RefDate());
    //    assertEquals("comment_1_briefsdc1_1", sampleIngest1.getLfsData().getComment1BriefSDC1());
    //    assertEquals("comment_1_briefsdc2_1", sampleIngest1.getLfsData().getComment1BriefSDC2());
    //    assertEquals("comment_1_briefsdc3_1", sampleIngest1.getLfsData().getComment1BriefSDC3());
    //    assertEquals("comment_2_refdte_1", sampleIngest1.getLfsData().getComment2RefDate());
    //    assertEquals("comment_2_briefsdc1_1", sampleIngest1.getLfsData().getComment2BriefSDC1());
    //    assertEquals("comment_2_briefsdc2_1", sampleIngest1.getLfsData().getComment2BriefSDC2());
    //    assertEquals("comment_2_briefsdc3_1", sampleIngest1.getLfsData().getComment2BriefSDC3());
    //    assertEquals("comment_3_refdte_1", sampleIngest1.getLfsData().getComment3RefDate());
    //    assertEquals("comment_3_briefsdc1_1", sampleIngest1.getLfsData().getComment3BriefSDC1());
    //    assertEquals("comment_3_briefsdc2_1", sampleIngest1.getLfsData().getComment3BriefSDC2());
    //    assertEquals("comment_3_briefsdc3_1", sampleIngest1.getLfsData().getComment3BriefSDC3());
    //    assertEquals("comment_4_refdte_1", sampleIngest1.getLfsData().getComment4RefDate());
    //    assertEquals("comment_4_briefsdc1_1", sampleIngest1.getLfsData().getComment4BriefSDC1());
    //    assertEquals("comment_4_briefsdc2_1", sampleIngest1.getLfsData().getComment4BriefSDC2());
    //    assertEquals("comment_4_briefsdc3_1", sampleIngest1.getLfsData().getComment4BriefSDC3());
    //    assertEquals("comment_5_refdte_1", sampleIngest1.getLfsData().getComment5RefDate());
    //    assertEquals("comment_5_briefsdc1_1", sampleIngest1.getLfsData().getComment5BriefSDC1());
    //    assertEquals("comment_5_briefsdc2_1", sampleIngest1.getLfsData().getComment5BriefSDC2());
    //    assertEquals("comment_5_briefsdc3_1", sampleIngest1.getLfsData().getComment5BriefSDC3());
  }

}

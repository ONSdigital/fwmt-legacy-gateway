package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;

@Data
@NoArgsConstructor
public class LegacySampleLFSDataIngest {
  @CSVColumn(value = "Quota_No", ignored = true)
  private String quotaNo;

  @CSVColumn("RefDate")
  private String refDate;

  @CSVColumn("LSTHO")
  private String lstho;

  @CSVColumn("MAIN")
  private String main;

  @CSVColumn("NUMHHLD")
  private String numberHouseholds;

  @CSVColumn("HHLDDESC")
  private String householdsDesc;

  // // // Names

  @CSVColumn("QRES_LINE_NAME_1")
  private String respondentName1;

  @CSVColumn("QRES_LINE_NAME_2")
  private String respondentName2;

  @CSVColumn("QRES_LINE_NAME_3")
  private String respondentName3;

  @CSVColumn("QRES_LINE_NAME_4")
  private String respondentName4;

  @CSVColumn("QRES_LINE_NAME_5")
  private String respondentName5;

  @CSVColumn("QRES_LINE_NAME_6")
  private String respondentName6;

  @CSVColumn("QRES_LINE_NAME_7")
  private String respondentName7;

  @CSVColumn("QRES_LINE_NAME_8")
  private String respondentName8;

  @CSVColumn("QRES_LINE_NAME_9")
  private String respondentName9;

  @CSVColumn("QRES_LINE_NAME_10")
  private String respondentName10;

  @CSVColumn("QRES_LINE_NAME_11")
  private String respondentName11;

  @CSVColumn("QRES_LINE_NAME_12")
  private String respondentName12;

  @CSVColumn("QRES_LINE_NAME_13")
  private String respondentName13;

  @CSVColumn("QRES_LINE_NAME_14")
  private String respondentName14;

  @CSVColumn("QRES_LINE_NAME_15")
  private String respondentName15;

  @CSVColumn("QRES_LINE_NAME_16")
  private String respondentName16;

  // // // Ages

  @CSVColumn("QRES_LINE_AGE_1")
  private String respondentAge1;

  @CSVColumn("QRES_LINE_AGE_2")
  private String respondentAge2;

  @CSVColumn("QRES_LINE_AGE_3")
  private String respondentAge3;

  @CSVColumn("QRES_LINE_AGE_4")
  private String respondentAge4;

  @CSVColumn("QRES_LINE_AGE_5")
  private String respondentAge5;

  @CSVColumn("QRES_LINE_AGE_6")
  private String respondentAge6;

  @CSVColumn("QRES_LINE_AGE_7")
  private String respondentAge7;

  @CSVColumn("QRES_LINE_AGE_8")
  private String respondentAge8;

  @CSVColumn("QRES_LINE_AGE_9")
  private String respondentAge9;

  @CSVColumn("QRES_LINE_AGE_10")
  private String respondentAge10;

  @CSVColumn("QRES_LINE_AGE_11")
  private String respondentAge11;

  @CSVColumn("QRES_LINE_AGE_12")
  private String respondentAge12;

  @CSVColumn("QRES_LINE_AGE_13")
  private String respondentAge13;

  @CSVColumn("QRES_LINE_AGE_14")
  private String respondentAge14;

  @CSVColumn("QRES_LINE_AGE_15")
  private String respondentAge15;

  @CSVColumn("QRES_LINE_AGE_16")
  private String respondentAge16;

  // // // Indicators

  // // Respondent 1

  @CSVColumn("QINDIV_1_WRKING")
  private String respondentWorkIndicator1;

  @CSVColumn(value = "QINDIV_1_JBAWAY", ignored = true)
  private String qindiv1Jbaway;

  @CSVColumn(value = "QINDIV_1_OWNBUS", ignored = true)
  private String qindiv1Ownbus;

  @CSVColumn(value = "QINDIV_1_RELBUS", ignored = true)
  private String qindiv1Relbus;

  @CSVColumn("QINDIV_1_LOOK4")
  private String respondentLookingForWork1;

  @CSVColumn(value = "QINDIV_1_DIFJOB", ignored = true)
  private String qindiv1Difjob;

  @CSVColumn("QINDIV_1_INDOUT")
  private String respondentInterviewType1;

  // // Respondent 2

  @CSVColumn("QINDIV_2_WRKING")
  private String respondentWorkIndicator2;

  @CSVColumn(value = "QINDIV_2_JBAWAY", ignored = true)
  private String qindiv2Jbaway;

  @CSVColumn(value = "QINDIV_2_OWNBUS", ignored = true)
  private String qindiv2Ownbus;

  @CSVColumn(value = "QINDIV_2_RELBUS", ignored = true)
  private String qindiv2Relbus;

  @CSVColumn("QINDIV_2_LOOK4")
  private String respondentLookingForWork2;

  @CSVColumn(value = "QINDIV_2_DIFJOB", ignored = true)
  private String qindiv2Difjob;

  @CSVColumn("QINDIV_2_INDOUT")
  private String respondentInterviewType2;

  // // Respondent 3

  @CSVColumn("QINDIV_3_WRKING")
  private String respondentWorkIndicator3;

  @CSVColumn(value = "QINDIV_3_JBAWAY", ignored = true)
  private String qindiv3Jbaway;

  @CSVColumn(value = "QINDIV_3_OWNBUS", ignored = true)
  private String qindiv3Ownbus;

  @CSVColumn(value = "QINDIV_3_RELBUS", ignored = true)
  private String qindiv3Relbus;

  @CSVColumn("QINDIV_3_LOOK4")
  private String respondentLookingForWork3;

  @CSVColumn(value = "QINDIV_3_DIFJOB", ignored = true)
  private String qindiv3Difjob;

  @CSVColumn("QINDIV_3_INDOUT")
  private String respondentInterviewType3;

  // // Respondent 4

  @CSVColumn("QINDIV_4_WRKING")
  private String respondentWorkIndicator4;

  @CSVColumn(value = "QINDIV_4_JBAWAY", ignored = true)
  private String qindiv4Jbaway;

  @CSVColumn(value = "QINDIV_4_OWNBUS", ignored = true)
  private String qindiv4Ownbus;

  @CSVColumn(value = "QINDIV_4_RELBUS", ignored = true)
  private String qindiv4Relbus;

  @CSVColumn("QINDIV_4_LOOK4")
  private String respondentLookingForWork4;

  @CSVColumn(value = "QINDIV_4_DIFJOB", ignored = true)
  private String qindiv4Difjob;

  @CSVColumn("QINDIV_4_INDOUT")
  private String respondentInterviewType4;

  // // Respondent 5

  @CSVColumn("QINDIV_5_WRKING")
  private String respondentWorkIndicator5;

  @CSVColumn(value = "QINDIV_5_JBAWAY", ignored = true)
  private String qindiv5Jbaway;

  @CSVColumn(value = "QINDIV_5_OWNBUS", ignored = true)
  private String qindiv5Ownbus;

  @CSVColumn(value = "QINDIV_5_RELBUS", ignored = true)
  private String qindiv5Relbus;

  @CSVColumn("QINDIV_5_LOOK4")
  private String respondentLookingForWork5;

  @CSVColumn(value = "QINDIV_5_DIFJOB", ignored = true)
  private String qindiv5Difjob;

  @CSVColumn("QINDIV_5_INDOUT")
  private String respondentInterviewType5;

  // // Respondent 6

  @CSVColumn("QINDIV_6_WRKING")
  private String respondentWorkIndicator6;

  @CSVColumn(value = "QINDIV_6_JBAWAY", ignored = true)
  private String qindiv6Jbaway;

  @CSVColumn(value = "QINDIV_6_OWNBUS", ignored = true)
  private String qindiv6Ownbus;

  @CSVColumn(value = "QINDIV_6_RELBUS", ignored = true)
  private String qindiv6Relbus;

  @CSVColumn("QINDIV_6_LOOK4")
  private String respondentLookingForWork6;

  @CSVColumn(value = "QINDIV_6_DIFJOB", ignored = true)
  private String qindiv6Difjob;

  @CSVColumn("QINDIV_6_INDOUT")
  private String respondentInterviewType6;

  // // Respondent 7

  @CSVColumn("QINDIV_7_WRKING")
  private String respondentWorkIndicator7;

  @CSVColumn(value = "QINDIV_7_JBAWAY", ignored = true)
  private String qindiv7Jbaway;

  @CSVColumn(value = "QINDIV_7_OWNBUS", ignored = true)
  private String qindiv7Ownbus;

  @CSVColumn(value = "QINDIV_7_RELBUS", ignored = true)
  private String qindiv7Relbus;

  @CSVColumn("QINDIV_7_LOOK4")
  private String respondentLookingForWork7;

  @CSVColumn(value = "QINDIV_7_DIFJOB", ignored = true)
  private String qindiv7Difjob;

  @CSVColumn("QINDIV_7_INDOUT")
  private String respondentInterviewType7;

  // // Respondent 8

  @CSVColumn("QINDIV_8_WRKING")
  private String respondentWorkIndicator8;

  @CSVColumn(value = "QINDIV_8_JBAWAY", ignored = true)
  private String qindiv8Jbaway;

  @CSVColumn(value = "QINDIV_8_OWNBUS", ignored = true)
  private String qindiv8Ownbus;

  @CSVColumn(value = "QINDIV_8_RELBUS", ignored = true)
  private String qindiv8Relbus;

  @CSVColumn("QINDIV_8_LOOK4")
  private String respondentLookingForWork8;

  @CSVColumn(value = "QINDIV_8_DIFJOB", ignored = true)
  private String qindiv8Difjob;

  @CSVColumn("QINDIV_8_INDOUT")
  private String respondentInterviewType8;

  // // Respondent 9

  @CSVColumn("QINDIV_9_WRKING")
  private String respondentWorkIndicator9;

  @CSVColumn(value = "QINDIV_9_JBAWAY", ignored = true)
  private String qindiv9Jbaway;

  @CSVColumn(value = "QINDIV_9_OWNBUS", ignored = true)
  private String qindiv9Ownbus;

  @CSVColumn(value = "QINDIV_9_RELBUS", ignored = true)
  private String qindiv9Relbus;

  @CSVColumn("QINDIV_9_LOOK4")
  private String respondentLookingForWork9;

  @CSVColumn(value = "QINDIV_9_DIFJOB", ignored = true)
  private String qindiv9Difjob;

  @CSVColumn("QINDIV_9_INDOUT")
  private String respondentInterviewType9;

  // // Respondent 10

  @CSVColumn("QINDIV_10_WRKING")
  private String respondentWorkIndicator10;

  @CSVColumn(value = "QINDIV_10_JBAWAY", ignored = true)
  private String qindiv10Jbaway;

  @CSVColumn(value = "QINDIV_10_OWNBUS", ignored = true)
  private String qindiv10Ownbus;

  @CSVColumn(value = "QINDIV_10_RELBUS", ignored = true)
  private String qindiv10Relbus;

  @CSVColumn("QINDIV_10_LOOK4")
  private String respondentLookingForWork10;

  @CSVColumn(value = "QINDIV_10_DIFJOB", ignored = true)
  private String qindiv10Difjob;

  @CSVColumn("QINDIV_10_INDOUT")
  private String respondentInterviewType10;

  // // Respondent 11

  @CSVColumn("QINDIV_11_WRKING")
  private String respondentWorkIndicator11;

  @CSVColumn(value = "QINDIV_11_JBAWAY", ignored = true)
  private String qindiv11Jbaway;

  @CSVColumn(value = "QINDIV_11_OWNBUS", ignored = true)
  private String qindiv11Ownbus;

  @CSVColumn(value = "QINDIV_11_RELBUS", ignored = true)
  private String qindiv11Relbus;

  @CSVColumn("QINDIV_11_LOOK4")
  private String respondentLookingForWork11;

  @CSVColumn(value = "QINDIV_11_DIFJOB", ignored = true)
  private String qindiv11Difjob;

  @CSVColumn("QINDIV_11_INDOUT")
  private String respondentInterviewType11;

  // // Respondent 12

  @CSVColumn("QINDIV_12_WRKING")
  private String respondentWorkIndicator12;

  @CSVColumn(value = "QINDIV_12_JBAWAY", ignored = true)
  private String qindiv12Jbaway;

  @CSVColumn(value = "QINDIV_12_OWNBUS", ignored = true)
  private String qindiv12Ownbus;

  @CSVColumn(value = "QINDIV_12_RELBUS", ignored = true)
  private String qindiv12Relbus;

  @CSVColumn("QINDIV_12_LOOK4")
  private String respondentLookingForWork12;

  @CSVColumn(value = "QINDIV_12_DIFJOB", ignored = true)
  private String qindiv12Difjob;

  @CSVColumn("QINDIV_12_INDOUT")
  private String respondentInterviewType12;

  // // Respondent 13

  @CSVColumn("QINDIV_13_WRKING")
  private String respondentWorkIndicator13;

  @CSVColumn(value = "QINDIV_13_JBAWAY", ignored = true)
  private String qindiv13Jbaway;

  @CSVColumn(value = "QINDIV_13_OWNBUS", ignored = true)
  private String qindiv13Ownbus;

  @CSVColumn(value = "QINDIV_13_RELBUS", ignored = true)
  private String qindiv13Relbus;

  @CSVColumn("QINDIV_13_LOOK4")
  private String respondentLookingForWork13;

  @CSVColumn(value = "QINDIV_13_DIFJOB", ignored = true)
  private String qindiv13Difjob;

  @CSVColumn("QINDIV_13_INDOUT")
  private String respondentInterviewType13;

  // // Respondent 14

  @CSVColumn("QINDIV_14_WRKING")
  private String respondentWorkIndicator14;

  @CSVColumn(value = "QINDIV_14_JBAWAY", ignored = true)
  private String qindiv14Jbaway;

  @CSVColumn(value = "QINDIV_14_OWNBUS", ignored = true)
  private String qindiv14Ownbus;

  @CSVColumn(value = "QINDIV_14_RELBUS", ignored = true)
  private String qindiv14Relbus;

  @CSVColumn("QINDIV_14_LOOK4")
  private String respondentLookingForWork14;

  @CSVColumn(value = "QINDIV_14_DIFJOB", ignored = true)
  private String qindiv14Difjob;

  @CSVColumn("QINDIV_14_INDOUT")
  private String respondentInterviewType14;

  // // Respondent 15

  @CSVColumn("QINDIV_15_WRKING")
  private String respondentWorkIndicator15;

  @CSVColumn(value = "QINDIV_15_JBAWAY", ignored = true)
  private String qindiv15Jbaway;

  @CSVColumn(value = "QINDIV_15_OWNBUS", ignored = true)
  private String qindiv15Ownbus;

  @CSVColumn(value = "QINDIV_15_RELBUS", ignored = true)
  private String qindiv15Relbus;

  @CSVColumn("QINDIV_15_LOOK4")
  private String respondentLookingForWork15;

  @CSVColumn(value = "QINDIV_15_DIFJOB", ignored = true)
  private String qindiv15Difjob;

  @CSVColumn("QINDIV_15_INDOUT")
  private String respondentInterviewType15;

  // // Respondent 16

  @CSVColumn("QINDIV_16_WRKING")
  private String respondentWorkIndicator16;

  @CSVColumn(value = "QINDIV_16_JBAWAY", ignored = true)
  private String qindiv16Jbaway;

  @CSVColumn(value = "QINDIV_16_OWNBUS", ignored = true)
  private String qindiv16Ownbus;

  @CSVColumn(value = "QINDIV_16_RELBUS", ignored = true)
  private String qindiv16Relbus;

  @CSVColumn("QINDIV_16_LOOK4")
  private String respondentLookingForWork16;

  @CSVColumn(value = "QINDIV_16_DIFJOB", ignored = true)
  private String qindiv16Difjob;

  @CSVColumn("QINDIV_16_INDOUT")
  private String respondentInterviewType16;

  // // // Notes?

  @CSVColumn(value = "tba")
  // TODO is this just a mis-spelling?
  private String respondentBriefNotes;

  // // // Other

  @CSVColumn(value = "WEEK", ignored = true)
  private String week;

  @CSVColumn(value = "W1YR", ignored = true)
  private String w1yr;

  @CSVColumn(value = "QRTR", ignored = true)
  private String qrtr;

  @CSVColumn(value = "WAVFND", ignored = true)
  private String wavfnd;

  @CSVColumn(value = "HHLD", ignored = true)
  private String hhld;

  @CSVColumn(value = "CHKLET", ignored = true)
  private String chklet;

  @CSVColumn(value = "DIVADDIND", ignored = true)
  private String divAddInd;

  @CSVColumn(value = "MO", ignored = true)
  private String mo;

  @CSVColumn(value = "HOUT", ignored = true)
  private String hOut;

  // duplicate LSTHO

  @CSVColumn(value = "LFSSAMP", ignored = true)
  private String lfsSamp;

  @CSVColumn(value = "THANKS", ignored = true)
  private String thanks;

  @CSVColumn(value = "THANKE", ignored = true)
  private String thanke;

  @CSVColumn(value = "RECPHONE", ignored = true)
  private String recPhone;

  @CSVColumn(value = "COUNTRY", ignored = true)
  private String country;

  @CSVColumn(value = "NUMPER", ignored = true)
  private String numper;

  // // // Comments

  // // Respondent 1

  @CSVColumn(value = "COMMENT_1_REFDTE", ignored = true)
  private String comment1RefDate;

  @CSVColumn("COMMENT_1_INTVNO")
  private String comment1InterviewerNo;

  @CSVColumn(value = "COMMENT_1_BriefSDC1", ignored = true)
  private String comment1BriefSDC1;

  @CSVColumn(value = "COMMENT_1_BriefSDC2", ignored = true)
  private String comment1BriefSDC2;

  @CSVColumn(value = "COMMENT_1_BriefSDC3", ignored = true)
  private String comment1BriefSDC3;

  @CSVColumn("COMMENT_1_BRIEF1")
  private String comment1BriefNotes1;

  // // Respondent 2

  @CSVColumn(value = "COMMENT_2_REFDTE", ignored = true)
  private String comment2RefDate;

  @CSVColumn("COMMENT_2_INTVNO")
  private String comment2InterviewerNo;

  @CSVColumn(value = "COMMENT_2_BriefSDC1", ignored = true)
  private String comment2BriefSDC1;

  @CSVColumn(value = "COMMENT_2_BriefSDC2", ignored = true)
  private String comment2BriefSDC2;

  @CSVColumn(value = "COMMENT_2_BriefSDC3", ignored = true)
  private String comment2BriefSDC3;

  @CSVColumn("COMMENT_2_BRIEF1")
  private String comment2BriefNotes1;

  // // Respondent 3

  @CSVColumn(value = "COMMENT_3_REFDTE", ignored = true)
  private String comment3RefDate;

  @CSVColumn("COMMENT_3_INTVNO")
  private String comment3InterviewerNo;

  @CSVColumn(value = "COMMENT_3_BriefSDC1", ignored = true)
  private String comment3BriefSDC1;

  @CSVColumn(value = "COMMENT_3_BriefSDC2", ignored = true)
  private String comment3BriefSDC2;

  @CSVColumn(value = "COMMENT_3_BriefSDC3", ignored = true)
  private String comment3BriefSDC3;

  @CSVColumn("COMMENT_3_BRIEF1")
  private String comment3BriefNotes1;

  // // Respondent 4

  @CSVColumn(value = "COMMENT_4_REFDTE", ignored = true)
  private String comment4RefDate;

  @CSVColumn("COMMENT_4_INTVNO")
  private String comment4InterviewerNo;

  @CSVColumn(value = "COMMENT_4_BriefSDC1", ignored = true)
  private String comment4BriefSDC1;

  @CSVColumn(value = "COMMENT_4_BriefSDC2", ignored = true)
  private String comment4BriefSDC2;

  @CSVColumn(value = "COMMENT_4_BriefSDC3", ignored = true)
  private String comment4BriefSDC3;

  @CSVColumn("COMMENT_4_BRIEF1")
  private String comment4BriefNotes1;

  // // Respondent 5

  @CSVColumn(value = "COMMENT_5_REFDTE", ignored = true)
  private String comment5RefDate;

  @CSVColumn("COMMENT_5_INTVNO")
  private String comment5InterviewerNo;

  @CSVColumn(value = "COMMENT_5_BriefSDC1", ignored = true)
  private String comment5BriefSDC1;

  @CSVColumn(value = "COMMENT_5_BriefSDC2", ignored = true)
  private String comment5BriefSDC2;

  @CSVColumn(value = "COMMENT_5_BriefSDC3", ignored = true)
  private String comment5BriefSDC3;

  @CSVColumn("COMMENT_5_BRIEF1")
  private String comment5BriefNotes1;

  // // // Other

  @CSVColumn("DIRECTION")
  private String direction;


}

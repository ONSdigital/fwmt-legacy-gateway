package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;

@Data
@NoArgsConstructor
public class LegacySampleLFSDataIngest {
  //@CSVColumn(value = "Quota_No", ignored = true)
  //private String quotaNo;

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

  //@CSVColumn(value = "QINDIV_1_JBAWAY", ignored = true)
  //private String qindiv1Jbaway;

  //CSVColumn(value = "QINDIV_1_OWNBUS", ignored = true)
  //private String qindiv1Ownbus;

  //CSVColumn(value = "QINDIV_1_RELBUS", ignored = true)
  //private String qindiv1Relbus;

  @CSVColumn("QINDIV_1_LOOK4")
  private String respondentLookingForWork1;

  //CSVColumn(value = "QINDIV_1_DIFJOB", ignored = true)
  //private String qindiv1Difjob;

  @CSVColumn("QINDIV_1_INDOUT")
  private String respondentInterviewType1;

  // // Respondent 2

  @CSVColumn("QINDIV_2_WRKING")
  private String respondentWorkIndicator2;

  //CSVColumn(value = "QINDIV_2_JBAWAY", ignored = true)
  //private String qindiv2Jbaway;

  //CSVColumn(value = "QINDIV_2_OWNBUS", ignored = true)
  //private String qindiv2Ownbus;

  //CSVColumn(value = "QINDIV_2_RELBUS", ignored = true)
  //private String qindiv2Relbus;

  @CSVColumn("QINDIV_2_LOOK4")
  private String respondentLookingForWork2;

  //CSVColumn(value = "QINDIV_2_DIFJOB", ignored = true)
  //private String qindiv2Difjob;

  @CSVColumn("QINDIV_2_INDOUT")
  private String respondentInterviewType2;

  // // Respondent 3

  @CSVColumn("QINDIV_3_WRKING")
  private String respondentWorkIndicator3;

  //CSVColumn(value = "QINDIV_3_JBAWAY", ignored = true)
  //private String qindiv3Jbaway;

  //CSVColumn(value = "QINDIV_3_OWNBUS", ignored = true)
  //private String qindiv3Ownbus;

  //CSVColumn(value = "QINDIV_3_RELBUS", ignored = true)
  //private String qindiv3Relbus;

  @CSVColumn("QINDIV_3_LOOK4")
  private String respondentLookingForWork3;

  //CSVColumn(value = "QINDIV_3_DIFJOB", ignored = true)
  //private String qindiv3Difjob;

  @CSVColumn("QINDIV_3_INDOUT")
  private String respondentInterviewType3;

  // // Respondent 4

  @CSVColumn("QINDIV_4_WRKING")
  private String respondentWorkIndicator4;

  //CSVColumn(value = "QINDIV_4_JBAWAY", ignored = true)
  //private String qindiv4Jbaway;

  //CSVColumn(value = "QINDIV_4_OWNBUS", ignored = true)
  //private String qindiv4Ownbus;

  //CSVColumn(value = "QINDIV_4_RELBUS", ignored = true)
  //private String qindiv4Relbus;

  @CSVColumn("QINDIV_4_LOOK4")
  private String respondentLookingForWork4;

  //CSVColumn(value = "QINDIV_4_DIFJOB", ignored = true)
  //private String qindiv4Difjob;

  @CSVColumn("QINDIV_4_INDOUT")
  private String respondentInterviewType4;

  // // Respondent 5

  @CSVColumn("QINDIV_5_WRKING")
  private String respondentWorkIndicator5;

  //CSVColumn(value = "QINDIV_5_JBAWAY", ignored = true)
  //private String qindiv5Jbaway;

  //CSVColumn(value = "QINDIV_5_OWNBUS", ignored = true)
  //private String qindiv5Ownbus;

  //CSVColumn(value = "QINDIV_5_RELBUS", ignored = true)
  //private String qindiv5Relbus;

  @CSVColumn("QINDIV_5_LOOK4")
  private String respondentLookingForWork5;

  //CSVColumn(value = "QINDIV_5_DIFJOB", ignored = true)
  //private String qindiv5Difjob;

  @CSVColumn("QINDIV_5_INDOUT")
  private String respondentInterviewType5;

  // // Respondent 6

  @CSVColumn("QINDIV_6_WRKING")
  private String respondentWorkIndicator6;

  //CSVColumn(value = "QINDIV_6_JBAWAY", ignored = true)
  //private String qindiv6Jbaway;

  //CSVColumn(value = "QINDIV_6_OWNBUS", ignored = true)
  //private String qindiv6Ownbus;

  //CSVColumn(value = "QINDIV_6_RELBUS", ignored = true)
  //private String qindiv6Relbus;

  @CSVColumn("QINDIV_6_LOOK4")
  private String respondentLookingForWork6;

  //CSVColumn(value = "QINDIV_6_DIFJOB", ignored = true)
  //private String qindiv6Difjob;

  @CSVColumn("QINDIV_6_INDOUT")
  private String respondentInterviewType6;

  // // Respondent 7

  @CSVColumn("QINDIV_7_WRKING")
  private String respondentWorkIndicator7;

  //CSVColumn(value = "QINDIV_7_JBAWAY", ignored = true)
  //private String qindiv7Jbaway;

  //CSVColumn(value = "QINDIV_7_OWNBUS", ignored = true)
  //private String qindiv7Ownbus;

  //CSVColumn(value = "QINDIV_7_RELBUS", ignored = true)
  //private String qindiv7Relbus;

  @CSVColumn("QINDIV_7_LOOK4")
  private String respondentLookingForWork7;

  //CSVColumn(value = "QINDIV_7_DIFJOB", ignored = true)
  //private String qindiv7Difjob;

  @CSVColumn("QINDIV_7_INDOUT")
  private String respondentInterviewType7;

  // // Respondent 8

  @CSVColumn("QINDIV_8_WRKING")
  private String respondentWorkIndicator8;

  //CSVColumn(value = "QINDIV_8_JBAWAY", ignored = true)
  //private String qindiv8Jbaway;

  //CSVColumn(value = "QINDIV_8_OWNBUS", ignored = true)
  //private String qindiv8Ownbus;

  //CSVColumn(value = "QINDIV_8_RELBUS", ignored = true)
  //private String qindiv8Relbus;

  @CSVColumn("QINDIV_8_LOOK4")
  private String respondentLookingForWork8;

  //CSVColumn(value = "QINDIV_8_DIFJOB", ignored = true)
  //private String qindiv8Difjob;

  @CSVColumn("QINDIV_8_INDOUT")
  private String respondentInterviewType8;

  // // Respondent 9

  @CSVColumn("QINDIV_9_WRKING")
  private String respondentWorkIndicator9;

  //CSVColumn(value = "QINDIV_9_JBAWAY", ignored = true)
  //private String qindiv9Jbaway;

  //CSVColumn(value = "QINDIV_9_OWNBUS", ignored = true)
  //private String qindiv9Ownbus;

  //CSVColumn(value = "QINDIV_9_RELBUS", ignored = true)
  //private String qindiv9Relbus;

  @CSVColumn("QINDIV_9_LOOK4")
  private String respondentLookingForWork9;

  //CSVColumn(value = "QINDIV_9_DIFJOB", ignored = true)
  //private String qindiv9Difjob;

  @CSVColumn("QINDIV_9_INDOUT")
  private String respondentInterviewType9;

  // // Respondent 10

  @CSVColumn("QINDIV_10_WRKING")
  private String respondentWorkIndicator10;

  //CSVColumn(value = "QINDIV_10_JBAWAY", ignored = true)
  //private String qindiv10Jbaway;

  //CSVColumn(value = "QINDIV_10_OWNBUS", ignored = true)
  //private String qindiv10Ownbus;

  //CSVColumn(value = "QINDIV_10_RELBUS", ignored = true)
  //private String qindiv10Relbus;

  @CSVColumn("QINDIV_10_LOOK4")
  private String respondentLookingForWork10;

  //CSVColumn(value = "QINDIV_10_DIFJOB", ignored = true)
  //private String qindiv10Difjob;

  @CSVColumn("QINDIV_10_INDOUT")
  private String respondentInterviewType10;

  // // Respondent 11

  @CSVColumn("QINDIV_11_WRKING")
  private String respondentWorkIndicator11;

  //CSVColumn(value = "QINDIV_11_JBAWAY", ignored = true)
  //private String qindiv11Jbaway;

  //CSVColumn(value = "QINDIV_11_OWNBUS", ignored = true)
  //private String qindiv11Ownbus;

  //CSVColumn(value = "QINDIV_11_RELBUS", ignored = true)
  //private String qindiv11Relbus;

  @CSVColumn("QINDIV_11_LOOK4")
  private String respondentLookingForWork11;

  //CSVColumn(value = "QINDIV_11_DIFJOB", ignored = true)
  //private String qindiv11Difjob;

  @CSVColumn("QINDIV_11_INDOUT")
  private String respondentInterviewType11;

  // // Respondent 12

  @CSVColumn("QINDIV_12_WRKING")
  private String respondentWorkIndicator12;

  //CSVColumn(value = "QINDIV_12_JBAWAY", ignored = true)
  //private String qindiv12Jbaway;

  //CSVColumn(value = "QINDIV_12_OWNBUS", ignored = true)
  //private String qindiv12Ownbus;

  //CSVColumn(value = "QINDIV_12_RELBUS", ignored = true)
  //private String qindiv12Relbus;

  @CSVColumn("QINDIV_12_LOOK4")
  private String respondentLookingForWork12;

  //CSVColumn(value = "QINDIV_12_DIFJOB", ignored = true)
  //private String qindiv12Difjob;

  @CSVColumn("QINDIV_12_INDOUT")
  private String respondentInterviewType12;

  // // Respondent 13

  @CSVColumn("QINDIV_13_WRKING")
  private String respondentWorkIndicator13;

  //CSVColumn(value = "QINDIV_13_JBAWAY", ignored = true)
  //private String qindiv13Jbaway;

  //CSVColumn(value = "QINDIV_13_OWNBUS", ignored = true)
  //private String qindiv13Ownbus;

  //CSVColumn(value = "QINDIV_13_RELBUS", ignored = true)
  //private String qindiv13Relbus;

  @CSVColumn("QINDIV_13_LOOK4")
  private String respondentLookingForWork13;

  //CSVColumn(value = "QINDIV_13_DIFJOB", ignored = true)
  //private String qindiv13Difjob;

  @CSVColumn("QINDIV_13_INDOUT")
  private String respondentInterviewType13;

  // // Respondent 14

  @CSVColumn("QINDIV_14_WRKING")
  private String respondentWorkIndicator14;

  //CSVColumn(value = "QINDIV_14_JBAWAY", ignored = true)
  //private String qindiv14Jbaway;

  //CSVColumn(value = "QINDIV_14_OWNBUS", ignored = true)
  //private String qindiv14Ownbus;

  //CSVColumn(value = "QINDIV_14_RELBUS", ignored = true)
  //private String qindiv14Relbus;

  @CSVColumn("QINDIV_14_LOOK4")
  private String respondentLookingForWork14;

  //CSVColumn(value = "QINDIV_14_DIFJOB", ignored = true)
  //private String qindiv14Difjob;

  @CSVColumn("QINDIV_14_INDOUT")
  private String respondentInterviewType14;

  // // Respondent 15

  @CSVColumn("QINDIV_15_WRKING")
  private String respondentWorkIndicator15;

  //CSVColumn(value = "QINDIV_15_JBAWAY", ignored = true)
  //private String qindiv15Jbaway;

  //CSVColumn(value = "QINDIV_15_OWNBUS", ignored = true)
  //private String qindiv15Ownbus;

  //CSVColumn(value = "QINDIV_15_RELBUS", ignored = true)
  //private String qindiv15Relbus;

  @CSVColumn("QINDIV_15_LOOK4")
  private String respondentLookingForWork15;

  //CSVColumn(value = "QINDIV_15_DIFJOB", ignored = true)
  //private String qindiv15Difjob;

  @CSVColumn("QINDIV_15_INDOUT")
  private String respondentInterviewType15;

  // // Respondent 16

  @CSVColumn("QINDIV_16_WRKING")
  private String respondentWorkIndicator16;

  //CSVColumn(value = "QINDIV_16_JBAWAY", ignored = true)
  //private String qindiv16Jbaway;

  //CSVColumn(value = "QINDIV_16_OWNBUS", ignored = true)
  //private String qindiv16Ownbus;

  //CSVColumn(value = "QINDIV_16_RELBUS", ignored = true)
  //private String qindiv16Relbus;

  @CSVColumn("QINDIV_16_LOOK4")
  private String respondentLookingForWork16;

  //CSVColumn(value = "QINDIV_16_DIFJOB", ignored = true)
  //private String qindiv16Difjob;

  @CSVColumn("QINDIV_16_INDOUT")
  private String respondentInterviewType16;

  // // // Notes?

  @CSVColumn(value = "tba")
  // TODO is this just a mis-spelling?
  private String respondentBriefNotes;

  // // // Other

  //CSVColumn(value = "WEEK", ignored = true)
  //private String week;

  //CSVColumn(value = "W1YR", ignored = true)
  //private String w1yr;

  //CSVColumn(value = "QRTR", ignored = true)
  //private String qrtr;

  //CSVColumn(value = "WAVFND", ignored = true)
  //private String wavfnd;

  //CSVColumn(value = "HHLD", ignored = true)
  //private String hhld;

  //CSVColumn(value = "CHKLET", ignored = true)
  //private String chklet;

  //CSVColumn(value = "DIVADDIND", ignored = true)
  //private String divAddInd;

  //CSVColumn(value = "MO", ignored = true)
  //private String mo;

  //CSVColumn(value = "HOUT", ignored = true)
  //private String hOut;

  //CSVColumn(value = "LSTHO", ignored = true)
  //private String lstho;

  //CSVColumn(value = "LFSSAMP", ignored = true)
  //private String lfsSamp;

  //CSVColumn(value = "THANKS", ignored = true)
  //private String thanks;

  //CSVColumn(value = "THANKE", ignored = true)
  //private String thanke;

  //CSVColumn(value = "RECPHONE", ignored = true)
  //private String recPhone;

  //CSVColumn(value = "COUNTRY", ignored = true)
  //private String country;

  //CSVColumn(value = "NUMPER", ignored = true)
  //private String numper;

  //CSVColumn(value = "NUMPER", ignored = true)
  //private String numper;

  // // // Comments

  // // Respondent 1

  //CSVColumn(value = "COMMENT_1_REFDTE", ignored = true)
  //private String comment1RefDate;

  @CSVColumn("COMMENT_1_INTVNO")
  private String comment1InterviewerNo;

  //CSVColumn(value = "COMMENT_1_BriefSDC1", ignored = true)
  //private String comment1BriefSDC1;

  //CSVColumn(value = "COMMENT_1_BriefSDC2", ignored = true)
  //private String comment1BriefSDC2;

  //CSVColumn(value = "COMMENT_1_BriefSDC3", ignored = true)
  //private String comment1BriefSDC3;

  @CSVColumn("COMMENT_1_BRIEF1")
  private String comment1BriefNotes1;

  // // Respondent 2

  //CSVColumn(value = "COMMENT_2_REFDTE", ignored = true)
  //private String comment2RefDate;

  @CSVColumn("COMMENT_2_INTVNO")
  private String comment2InterviewerNo;

  //CSVColumn(value = "COMMENT_2_BriefSDC1", ignored = true)
  //private String comment2BriefSDC1;

  //CSVColumn(value = "COMMENT_2_BriefSDC2", ignored = true)
  //private String comment2BriefSDC2;

  //CSVColumn(value = "COMMENT_2_BriefSDC3", ignored = true)
  //private String comment2BriefSDC3;

  @CSVColumn("COMMENT_2_BRIEF1")
  private String comment2BriefNotes1;

  // // Respondent 3

  //CSVColumn(value = "COMMENT_3_REFDTE", ignored = true)
  //private String comment3RefDate;

  @CSVColumn("COMMENT_3_INTVNO")
  private String comment3InterviewerNo;

  //CSVColumn(value = "COMMENT_3_BriefSDC1", ignored = true)
  //private String comment3BriefSDC1;

  //CSVColumn(value = "COMMENT_3_BriefSDC2", ignored = true)
  //private String comment3BriefSDC2;

  //CSVColumn(value = "COMMENT_3_BriefSDC3", ignored = true)
  //private String comment3BriefSDC3;

  @CSVColumn("COMMENT_3_BRIEF1")
  private String comment3BriefNotes1;

  // // Respondent 4

  //CSVColumn(value = "COMMENT_4_REFDTE", ignored = true)
  //private String comment4RefDate;

  @CSVColumn("COMMENT_4_INTVNO")
  private String comment4InterviewerNo;

  //CSVColumn(value = "COMMENT_4_BriefSDC1", ignored = true)
  //private String comment4BriefSDC1;

  //CSVColumn(value = "COMMENT_4_BriefSDC2", ignored = true)
  //private String comment4BriefSDC2;

  //CSVColumn(value = "COMMENT_4_BriefSDC3", ignored = true)
  //private String comment4BriefSDC3;

  @CSVColumn("COMMENT_4_BRIEF1")
  private String comment4BriefNotes1;

  // // Respondent 5

  //CSVColumn(value = "COMMENT_5_REFDTE", ignored = true)
  //private String comment5RefDate;

  @CSVColumn("COMMENT_5_INTVNO")
  private String comment5InterviewerNo;

  //CSVColumn(value = "COMMENT_5_BriefSDC1", ignored = true)
  //private String comment5BriefSDC1;

  //CSVColumn(value = "COMMENT_5_BriefSDC2", ignored = true)
  //private String comment5BriefSDC2;

  //CSVColumn(value = "COMMENT_5_BriefSDC3", ignored = true)
  //private String comment5BriefSDC3;

  @CSVColumn("COMMENT_5_BRIEF1")
  private String comment5BriefNotes1;

  // // // Other

  @CSVColumn("DIRECTION")
  private String direction;



//  public LegacySampleLFSDataIngest(CSVRecord record) {
//    //this.quotaNo = record.get("Quota_No");
//    this.refDate = (record.isSet("RefDate")) ? record.get("RefDate") : null;
//    this.lstho = (record.isSet("LSTHO")) ? record.get("LSTHO") : null;
//    this.main = (record.isSet("MAIN")) ? record.get("MAIN") : null;
//    this.numberHouseholds = (record.isSet("NUMHHLD")) ? record.get("NUMHHLD") : null;
//    this.householdsDesc = (record.isSet("HHLDDESC")) ? record.get("HHLDDESC") : null;
//    // // // Names
//    this.respondentName1 = (record.isSet("QRES_LINE_NAME_1")) ? record.get("QRES_LINE_NAME_1") : null;
//    this.respondentName2 = (record.isSet("QRES_LINE_NAME_2")) ? record.get("QRES_LINE_NAME_2") : null;
//    this.respondentName3 = (record.isSet("QRES_LINE_NAME_3")) ? record.get("QRES_LINE_NAME_3") : null;
//    this.respondentName4 = (record.isSet("QRES_LINE_NAME_4")) ? record.get("QRES_LINE_NAME_4") : null;
//    this.respondentName5 = (record.isSet("QRES_LINE_NAME_5")) ? record.get("QRES_LINE_NAME_5") : null;
//    this.respondentName6 = (record.isSet("QRES_LINE_NAME_6")) ? record.get("QRES_LINE_NAME_6") : null;
//    this.respondentName7 = (record.isSet("QRES_LINE_NAME_7")) ? record.get("QRES_LINE_NAME_7") : null;
//    this.respondentName8 = (record.isSet("QRES_LINE_NAME_8")) ? record.get("QRES_LINE_NAME_8") : null;
//    this.respondentName9 = (record.isSet("QRES_LINE_NAME_9")) ? record.get("QRES_LINE_NAME_9") : null;
//    this.respondentName10 = (record.isSet("QRES_LINE_NAME_10")) ? record.get("QRES_LINE_NAME_10") : null;
//    this.respondentName11 = (record.isSet("QRES_LINE_NAME_11")) ? record.get("QRES_LINE_NAME_11") : null;
//    this.respondentName12 = (record.isSet("QRES_LINE_NAME_12")) ? record.get("QRES_LINE_NAME_12") : null;
//    this.respondentName13 = (record.isSet("QRES_LINE_NAME_13")) ? record.get("QRES_LINE_NAME_13") : null;
//    this.respondentName14 = (record.isSet("QRES_LINE_NAME_14")) ? record.get("QRES_LINE_NAME_14") : null;
//    this.respondentName15 = (record.isSet("QRES_LINE_NAME_15")) ? record.get("QRES_LINE_NAME_15") : null;
//    this.respondentName16 = (record.isSet("QRES_LINE_NAME_16")) ? record.get("QRES_LINE_NAME_16") : null;
//    // // // Ages
//    this.respondentAge1 = (record.isSet("QRES_LINE_AGE_1")) ? record.get("QRES_LINE_AGE_1") : null;
//    this.respondentAge2 = (record.isSet("QRES_LINE_AGE_2")) ? record.get("QRES_LINE_AGE_2") : null;
//    this.respondentAge3 = (record.isSet("QRES_LINE_AGE_3")) ? record.get("QRES_LINE_AGE_3") : null;
//    this.respondentAge4 = (record.isSet("QRES_LINE_AGE_4")) ? record.get("QRES_LINE_AGE_4") : null;
//    this.respondentAge5 = (record.isSet("QRES_LINE_AGE_5")) ? record.get("QRES_LINE_AGE_5") : null;
//    this.respondentAge6 = (record.isSet("QRES_LINE_AGE_6")) ? record.get("QRES_LINE_AGE_6") : null;
//    this.respondentAge7 = (record.isSet("QRES_LINE_AGE_7")) ? record.get("QRES_LINE_AGE_7") : null;
//    this.respondentAge8 = (record.isSet("QRES_LINE_AGE_8")) ? record.get("QRES_LINE_AGE_8") : null;
//    this.respondentAge9 = (record.isSet("QRES_LINE_AGE_9")) ? record.get("QRES_LINE_AGE_9") : null;
//    this.respondentAge10 = (record.isSet("QRES_LINE_AGE_10")) ? record.get("QRES_LINE_AGE_10") : null;
//    this.respondentAge11 = (record.isSet("QRES_LINE_AGE_11")) ? record.get("QRES_LINE_AGE_11") : null;
//    this.respondentAge12 = (record.isSet("QRES_LINE_AGE_12")) ? record.get("QRES_LINE_AGE_12") : null;
//    this.respondentAge13 = (record.isSet("QRES_LINE_AGE_13")) ? record.get("QRES_LINE_AGE_13") : null;
//    this.respondentAge14 = (record.isSet("QRES_LINE_AGE_14")) ? record.get("QRES_LINE_AGE_14") : null;
//    this.respondentAge15 = (record.isSet("QRES_LINE_AGE_15")) ? record.get("QRES_LINE_AGE_15") : null;
//    this.respondentAge16 = (record.isSet("QRES_LINE_AGE_16")) ? record.get("QRES_LINE_AGE_16") : null;
//    // // // Indicators
//    // // Respondent 1
//    this.respondentWorkIndicator1 = (record.isSet("QINDIV_1_WRKING")) ? record.get("QINDIV_1_WRKING") : null;
//    //this.qindiv1Jbaway = (record.isSet("QINDIV_1_JBAWAY")) ? record.get("QINDIV_1_JBAWAY") : null;
//    //this.qindiv1Ownbus = (record.isSet("QINDIV_1_OWNBUS")) ? record.get("QINDIV_1_OWNBUS") : null;
//    //this.qindiv1Relbus = (record.isSet("QINDIV_1_RELBUS")) ? record.get("QINDIV_1_RELBUS") : null;
//    this.respondentLookingForWork1 = (record.isSet("QINDIV_1_LOOK4")) ? record.get("QINDIV_1_LOOK4") : null;
//    //this.qindiv1Difjob = (record.isSet("QINDIV_1_DIFJOB")) ? record.get("QINDIV_1_DIFJOB") : null;
//    this.respondentInterviewType1 = (record.isSet("QINDIV_1_INDOUT")) ? record.get("QINDIV_1_INDOUT") : null;
//    // // Respondent 2
//    this.respondentWorkIndicator2 = (record.isSet("QINDIV_2_WRKING")) ? record.get("QINDIV_2_WRKING") : null;
//    //this.qindiv2Jbaway = (record.isSet("QINDIV_2_JBAWAY")) ? record.get("QINDIV_2_JBAWAY") : null;
//    //this.qindiv2Ownbus = (record.isSet("QINDIV_2_OWNBUS")) ? record.get("QINDIV_2_OWNBUS") : null;
//    //this.qindiv2Relbus = (record.isSet("QINDIV_2_RELBUS")) ? record.get("QINDIV_2_RELBUS") : null;
//    this.respondentLookingForWork2 = (record.isSet("QINDIV_2_LOOK4")) ? record.get("QINDIV_2_LOOK4") : null;
//    //this.qindiv2Difjob = (record.isSet("QINDIV_2_DIFJOB")) ? record.get("QINDIV_2_DIFJOB") : null;
//    this.respondentInterviewType2 = (record.isSet("QINDIV_2_INDOUT")) ? record.get("QINDIV_2_INDOUT") : null;
//    // // Respondent 3
//    this.respondentWorkIndicator3 = (record.isSet("QINDIV_3_WRKING")) ? record.get("QINDIV_3_WRKING") : null;
//    //this.qindiv3Jbaway = (record.isSet("QINDIV_3_JBAWAY")) ? record.get("QINDIV_3_JBAWAY") : null;
//    //this.qindiv3Ownbus = (record.isSet("QINDIV_3_OWNBUS")) ? record.get("QINDIV_3_OWNBUS") : null;
//    //this.qindiv3Relbus = (record.isSet("QINDIV_3_RELBUS")) ? record.get("QINDIV_3_RELBUS") : null;
//    this.respondentLookingForWork3 = (record.isSet("QINDIV_3_LOOK4")) ? record.get("QINDIV_3_LOOK4") : null;
//    //this.qindiv3Difjob = (record.isSet("QINDIV_3_DIFJOB")) ? record.get("QINDIV_3_DIFJOB") : null;
//    this.respondentInterviewType3 = (record.isSet("QINDIV_3_INDOUT")) ? record.get("QINDIV_3_INDOUT") : null;
//    // // Respondent 4
//    this.respondentWorkIndicator4 = (record.isSet("QINDIV_4_WRKING")) ? record.get("QINDIV_4_WRKING") : null;
//    //this.qindiv4Jbaway = (record.isSet("QINDIV_4_JBAWAY")) ? record.get("QINDIV_4_JBAWAY") : null;
//    //this.qindiv4Ownbus = (record.isSet("QINDIV_4_OWNBUS")) ? record.get("QINDIV_4_OWNBUS") : null;
//    //this.qindiv4Relbus = (record.isSet("QINDIV_4_RELBUS")) ? record.get("QINDIV_4_RELBUS") : null;
//    this.respondentLookingForWork4 = (record.isSet("QINDIV_4_LOOK4")) ? record.get("QINDIV_4_LOOK4") : null;
//    //this.qindiv4Difjob = (record.isSet("QINDIV_4_DIFJOB")) ? record.get("QINDIV_4_DIFJOB") : null;
//    this.respondentInterviewType4 = (record.isSet("QINDIV_4_INDOUT")) ? record.get("QINDIV_4_INDOUT") : null;
//    // // Respondent 5
//    this.respondentWorkIndicator5 = (record.isSet("QINDIV_5_WRKING")) ? record.get("QINDIV_5_WRKING") : null;
//    //this.qindiv5Jbaway = (record.isSet("QINDIV_5_JBAWAY")) ? record.get("QINDIV_5_JBAWAY") : null;
//    //this.qindiv5Ownbus = (record.isSet("QINDIV_5_OWNBUS")) ? record.get("QINDIV_5_OWNBUS") : null;
//    //this.qindiv5Relbus = (record.isSet("QINDIV_5_RELBUS")) ? record.get("QINDIV_5_RELBUS") : null;
//    this.respondentLookingForWork5 = (record.isSet("QINDIV_5_LOOK4")) ? record.get("QINDIV_5_LOOK4") : null;
//    //this.qindiv5Difjob = (record.isSet("QINDIV_5_DIFJOB")) ? record.get("QINDIV_5_DIFJOB") : null;
//    this.respondentInterviewType5 = (record.isSet("QINDIV_5_INDOUT")) ? record.get("QINDIV_5_INDOUT") : null;
//    // // Respondent 6
//    this.respondentWorkIndicator6 = (record.isSet("QINDIV_6_WRKING")) ? record.get("QINDIV_6_WRKING") : null;
//    //this.qindiv6Jbaway = (record.isSet("QINDIV_6_JBAWAY")) ? record.get("QINDIV_6_JBAWAY") : null;
//    //this.qindiv6Ownbus = (record.isSet("QINDIV_6_OWNBUS")) ? record.get("QINDIV_6_OWNBUS") : null;
//    //this.qindiv6Relbus = (record.isSet("QINDIV_6_RELBUS")) ? record.get("QINDIV_6_RELBUS") : null;
//    this.respondentLookingForWork6 = (record.isSet("QINDIV_6_LOOK4")) ? record.get("QINDIV_6_LOOK4") : null;
//    //this.qindiv6Difjob = (record.isSet("QINDIV_6_DIFJOB")) ? record.get("QINDIV_6_DIFJOB") : null;
//    this.respondentInterviewType6 = (record.isSet("QINDIV_6_INDOUT")) ? record.get("QINDIV_6_INDOUT") : null;
//    // // Respondent 7
//    this.respondentWorkIndicator7 = (record.isSet("QINDIV_7_WRKING")) ? record.get("QINDIV_7_WRKING") : null;
//    //this.qindiv7Jbaway = (record.isSet("QINDIV_7_JBAWAY")) ? record.get("QINDIV_7_JBAWAY") : null;
//    //this.qindiv7Ownbus = (record.isSet("QINDIV_7_OWNBUS")) ? record.get("QINDIV_7_OWNBUS") : null;
//    //this.qindiv7Relbus = (record.isSet("QINDIV_7_RELBUS")) ? record.get("QINDIV_7_RELBUS") : null;
//    this.respondentLookingForWork7 = (record.isSet("QINDIV_7_LOOK4")) ? record.get("QINDIV_7_LOOK4") : null;
//    //this.qindiv7Difjob = (record.isSet("QINDIV_7_DIFJOB")) ? record.get("QINDIV_7_DIFJOB") : null;
//    this.respondentInterviewType7 = (record.isSet("QINDIV_7_INDOUT")) ? record.get("QINDIV_7_INDOUT") : null;
//    // // Respondent 8
//    this.respondentWorkIndicator8 = (record.isSet("QINDIV_8_WRKING")) ? record.get("QINDIV_8_WRKING") : null;
//    //this.qindiv8Jbaway = (record.isSet("QINDIV_8_JBAWAY")) ? record.get("QINDIV_8_JBAWAY") : null;
//    //this.qindiv8Ownbus = (record.isSet("QINDIV_8_OWNBUS")) ? record.get("QINDIV_8_OWNBUS") : null;
//    //this.qindiv8Relbus = (record.isSet("QINDIV_8_RELBUS")) ? record.get("QINDIV_8_RELBUS") : null;
//    this.respondentLookingForWork8 = (record.isSet("QINDIV_8_LOOK4")) ? record.get("QINDIV_8_LOOK4") : null;
//    //this.qindiv8Difjob = (record.isSet("QINDIV_8_DIFJOB")) ? record.get("QINDIV_8_DIFJOB") : null;
//    this.respondentInterviewType8 = (record.isSet("QINDIV_8_INDOUT")) ? record.get("QINDIV_8_INDOUT") : null;
//    // // Respondent 9
//    this.respondentWorkIndicator9 = (record.isSet("QINDIV_9_WRKING")) ? record.get("QINDIV_9_WRKING") : null;
//    //this.qindiv9Jbaway = (record.isSet("QINDIV_9_JBAWAY")) ? record.get("QINDIV_9_JBAWAY") : null;
//    //this.qindiv9Ownbus = (record.isSet("QINDIV_9_OWNBUS")) ? record.get("QINDIV_9_OWNBUS") : null;
//    //this.qindiv9Relbus = (record.isSet("QINDIV_9_RELBUS")) ? record.get("QINDIV_9_RELBUS") : null;
//    this.respondentLookingForWork9 = (record.isSet("QINDIV_9_LOOK4")) ? record.get("QINDIV_9_LOOK4") : null;
//    //this.qindiv9Difjob = (record.isSet("QINDIV_9_DIFJOB")) ? record.get("QINDIV_9_DIFJOB") : null;
//    this.respondentInterviewType9 = (record.isSet("QINDIV_9_INDOUT")) ? record.get("QINDIV_9_INDOUT") : null;
//    // // Respondent 10
//    this.respondentWorkIndicator10 = (record.isSet("QINDIV_10_WRKING")) ? record.get("QINDIV_10_WRKING") : null;
//    //this.qindiv10Jbaway = (record.isSet("QINDIV_10_JBAWAY")) ? record.get("QINDIV_10_JBAWAY") : null;
//    //this.qindiv10Ownbus = (record.isSet("QINDIV_10_OWNBUS")) ? record.get("QINDIV_10_OWNBUS") : null;
//    //this.qindiv10Relbus = (record.isSet("QINDIV_10_RELBUS")) ? record.get("QINDIV_10_RELBUS") : null;
//    this.respondentLookingForWork10 = (record.isSet("QINDIV_10_LOOK4")) ? record.get("QINDIV_10_LOOK4") : null;
//    //this.qindiv10Difjob = (record.isSet("QINDIV_10_DIFJOB")) ? record.get("QINDIV_10_DIFJOB") : null;
//    this.respondentInterviewType10 = (record.isSet("QINDIV_10_INDOUT")) ? record.get("QINDIV_10_INDOUT") : null;
//    // // Respondent 11
//    this.respondentWorkIndicator11 = (record.isSet("QINDIV_11_WRKING")) ? record.get("QINDIV_11_WRKING") : null;
//    //this.qindiv11Jbaway = (record.isSet("QINDIV_11_JBAWAY")) ? record.get("QINDIV_11_JBAWAY") : null;
//    //this.qindiv11Ownbus = (record.isSet("QINDIV_11_OWNBUS")) ? record.get("QINDIV_11_OWNBUS") : null;
//    //this.qindiv11Relbus = (record.isSet("QINDIV_11_RELBUS")) ? record.get("QINDIV_11_RELBUS") : null;
//    this.respondentLookingForWork11 = (record.isSet("QINDIV_11_LOOK4")) ? record.get("QINDIV_11_LOOK4") : null;
//    //this.qindiv11Difjob = (record.isSet("QINDIV_11_DIFJOB")) ? record.get("QINDIV_11_DIFJOB") : null;
//    this.respondentInterviewType11 = (record.isSet("QINDIV_11_INDOUT")) ? record.get("QINDIV_11_INDOUT") : null;
//    // // Respondent 12
//    this.respondentWorkIndicator12 = (record.isSet("QINDIV_12_WRKING")) ? record.get("QINDIV_12_WRKING") : null;
//    //this.qindiv12Jbaway = (record.isSet("QINDIV_12_JBAWAY")) ? record.get("QINDIV_12_JBAWAY") : null;
//    //this.qindiv12Ownbus = (record.isSet("QINDIV_12_OWNBUS")) ? record.get("QINDIV_12_OWNBUS") : null;
//    //this.qindiv12Relbus = (record.isSet("QINDIV_12_RELBUS")) ? record.get("QINDIV_12_RELBUS") : null;
//    this.respondentLookingForWork12 = (record.isSet("QINDIV_12_LOOK4")) ? record.get("QINDIV_12_LOOK4") : null;
//    //this.qindiv12Difjob = (record.isSet("QINDIV_12_DIFJOB")) ? record.get("QINDIV_12_DIFJOB") : null;
//    this.respondentInterviewType12 = (record.isSet("QINDIV_12_INDOUT")) ? record.get("QINDIV_12_INDOUT") : null;
//    // // Respondent 13
//    this.respondentWorkIndicator13 = (record.isSet("QINDIV_13_WRKING")) ? record.get("QINDIV_13_WRKING") : null;
//    //this.qindiv13Jbaway = (record.isSet("QINDIV_13_JBAWAY")) ? record.get("QINDIV_13_JBAWAY") : null;
//    //this.qindiv13Ownbus = (record.isSet("QINDIV_13_OWNBUS")) ? record.get("QINDIV_13_OWNBUS") : null;
//    //this.qindiv13Relbus = (record.isSet("QINDIV_13_RELBUS")) ? record.get("QINDIV_13_RELBUS") : null;
//    this.respondentLookingForWork13 = (record.isSet("QINDIV_13_LOOK4")) ? record.get("QINDIV_13_LOOK4") : null;
//    //this.qindiv13Difjob = (record.isSet("QINDIV_13_DIFJOB")) ? record.get("QINDIV_13_DIFJOB") : null;
//    this.respondentInterviewType13 = (record.isSet("QINDIV_13_INDOUT")) ? record.get("QINDIV_13_INDOUT") : null;
//    // // Respondent 14
//    this.respondentWorkIndicator14 = (record.isSet("QINDIV_14_WRKING")) ? record.get("QINDIV_14_WRKING") : null;
//    //this.qindiv14Jbaway = (record.isSet("QINDIV_14_JBAWAY")) ? record.get("QINDIV_14_JBAWAY") : null;
//    //this.qindiv14Ownbus = (record.isSet("QINDIV_14_OWNBUS")) ? record.get("QINDIV_14_OWNBUS") : null;
//    //this.qindiv14Relbus = (record.isSet("QINDIV_14_RELBUS")) ? record.get("QINDIV_14_RELBUS") : null;
//    this.respondentLookingForWork14 = (record.isSet("QINDIV_14_LOOK4")) ? record.get("QINDIV_14_LOOK4") : null;
//    //this.qindiv14Difjob = (record.isSet("QINDIV_14_DIFJOB")) ? record.get("QINDIV_14_DIFJOB") : null;
//    this.respondentInterviewType14 = (record.isSet("QINDIV_14_INDOUT")) ? record.get("QINDIV_14_INDOUT") : null;
//    // // Respondent 15
//    this.respondentWorkIndicator15 = (record.isSet("QINDIV_15_WRKING")) ? record.get("QINDIV_15_WRKING") : null;
//    //this.qindiv15Jbaway = (record.isSet("QINDIV_15_JBAWAY")) ? record.get("QINDIV_15_JBAWAY") : null;
//    //this.qindiv15Ownbus = (record.isSet("QINDIV_15_OWNBUS")) ? record.get("QINDIV_15_OWNBUS") : null;
//    //this.qindiv15Relbus = (record.isSet("QINDIV_15_RELBUS")) ? record.get("QINDIV_15_RELBUS") : null;
//    this.respondentLookingForWork15 = (record.isSet("QINDIV_15_LOOK4")) ? record.get("QINDIV_15_LOOK4") : null;
//    //this.qindiv15Difjob = (record.isSet("QINDIV_15_DIFJOB")) ? record.get("QINDIV_15_DIFJOB") : null;
//    this.respondentInterviewType15 = (record.isSet("QINDIV_15_INDOUT")) ? record.get("QINDIV_15_INDOUT") : null;
//    // // Respondent 16
//    this.respondentWorkIndicator16 = (record.isSet("QINDIV_16_WRKING")) ? record.get("QINDIV_16_WRKING") : null;
//    //this.qindiv16Jbaway = (record.isSet("QINDIV_16_JBAWAY")) ? record.get("QINDIV_16_JBAWAY") : null;
//    //this.qindiv16Ownbus = (record.isSet("QINDIV_16_OWNBUS")) ? record.get("QINDIV_16_OWNBUS") : null;
//    //this.qindiv16Relbus = (record.isSet("QINDIV_16_RELBUS")) ? record.get("QINDIV_16_RELBUS") : null;
//    this.respondentLookingForWork16 = (record.isSet("QINDIV_16_LOOK4")) ? record.get("QINDIV_16_LOOK4") : null;
//    //this.qindiv16Difjob = (record.isSet("QINDIV_16_DIFJOB")) ? record.get("QINDIV_16_DIFJOB") : null;
//    this.respondentInterviewType16 = (record.isSet("QINDIV_16_INDOUT")) ? record.get("QINDIV_16_INDOUT") : null;
//    // // // Notes?
//    // TODO is this definitely under 'tba'?
//    this.respondentBriefNotes = (record.isSet("tba")) ? record.get("tba") : null;
//    // // // Other
//    //this.week = (record.isSet("WEEK")) ? record.get("WEEK") : null;
//    //this.w1yr = (record.isSet("W1YR")) ? record.get("W1YR") : null;
//    //this.qrtr = (record.isSet("QRTR")) ? record.get("QRTR") : null;
//    //this.wavfnd = (record.isSet("WAVFND")) ? record.get("WAVFND") : null;
//    //this.hhld = (record.isSet("HHLD")) ? record.get("HHLD") : null;
//    //this.chklet = (record.isSet("CHKLET")) ? record.get("CHKLET") : null;
//    //this.divAddInd = (record.isSet("DIVADDIND")) ? record.get("DIVADDIND") : null;
//    //this.mo = (record.isSet("MO")) ? record.get("MO") : null;
//    //this.hOut = (record.isSet("HOUT")) ? record.get("HOUT") : null;
//    //this.lstho = (record.isSet("LSTHO")) ? record.get("LSTHO") : null;
//    //this.lfsSamp = (record.isSet("LFSSAMP")) ? record.get("LFSSAMP") : null;
//    //this.thanks = (record.isSet("THANKS")) ? record.get("THANKS") : null;
//    //this.thanke = (record.isSet("THANKE")) ? record.get("THANKE") : null;
//    //this.recPhone = (record.isSet("RECPHONE")) ? record.get("RECPHONE") : null;
//    //this.country = (record.isSet("COUNTRY")) ? record.get("COUNTRY") : null;
//    //this.numper = (record.isSet("NUMPER")) ? record.get("NUMPER") : null;
//    //this.numper = (record.isSet("NUMPER")) ? record.get("NUMPER") : null;
//    // // // Comments
//    // // Respondent 1
//    //this.comment1RefDate = (record.isSet("COMMENT_1_REFDTE")) ? record.get("COMMENT_1_REFDTE") : null;
//    this.comment1InterviewerNo = (record.isSet("COMMENT_1_INTVNO")) ? record.get("COMMENT_1_INTVNO") : null;
//    //this.comment1BriefSDC1 = (record.isSet("COMMENT_1_BriefSDC1")) ? record.get("COMMENT_1_BriefSDC1") : null;
//    //this.comment1BriefSDC2 = (record.isSet("COMMENT_1_BriefSDC2")) ? record.get("COMMENT_1_BriefSDC2") : null;
//    //this.comment1BriefSDC3 = (record.isSet("COMMENT_1_BriefSDC3")) ? record.get("COMMENT_1_BriefSDC3") : null;
//    this.comment1BriefNotes1 = (record.isSet("COMMENT_1_BRIEF1")) ? record.get("COMMENT_1_BRIEF1") : null;
//    // // Respondent 2
//    //this.comment2RefDate = (record.isSet("COMMENT_2_REFDTE")) ? record.get("COMMENT_2_REFDTE") : null;
//    this.comment2InterviewerNo = (record.isSet("COMMENT_2_INTVNO")) ? record.get("COMMENT_2_INTVNO") : null;
//    //this.comment2BriefSDC1 = (record.isSet("COMMENT_2_BriefSDC1")) ? record.get("COMMENT_2_BriefSDC1") : null;
//    //this.comment2BriefSDC2 = (record.isSet("COMMENT_2_BriefSDC2")) ? record.get("COMMENT_2_BriefSDC2") : null;
//    //this.comment2BriefSDC3 = (record.isSet("COMMENT_2_BriefSDC3")) ? record.get("COMMENT_2_BriefSDC3") : null;
//    this.comment2BriefNotes1 = (record.isSet("COMMENT_2_BRIEF1")) ? record.get("COMMENT_2_BRIEF1") : null;
//    // // Respondent 3
//    //this.comment3RefDate = (record.isSet("COMMENT_3_REFDTE")) ? record.get("COMMENT_3_REFDTE") : null;
//    this.comment3InterviewerNo = (record.isSet("COMMENT_3_INTVNO")) ? record.get("COMMENT_3_INTVNO") : null;
//    //this.comment3BriefSDC1 = (record.isSet("COMMENT_3_BriefSDC1")) ? record.get("COMMENT_3_BriefSDC1") : null;
//    //this.comment3BriefSDC2 = (record.isSet("COMMENT_3_BriefSDC2")) ? record.get("COMMENT_3_BriefSDC2") : null;
//    //this.comment3BriefSDC3 = (record.isSet("COMMENT_3_BriefSDC3")) ? record.get("COMMENT_3_BriefSDC3") : null;
//    this.comment3BriefNotes1 = (record.isSet("COMMENT_3_BRIEF1")) ? record.get("COMMENT_3_BRIEF1") : null;
//    // // Respondent 4
//    //this.comment4RefDate = (record.isSet("COMMENT_4_REFDTE")) ? record.get("COMMENT_4_REFDTE") : null;
//    this.comment4InterviewerNo = (record.isSet("COMMENT_4_INTVNO")) ? record.get("COMMENT_4_INTVNO") : null;
//    //this.comment4BriefSDC1 = (record.isSet("COMMENT_4_BriefSDC1")) ? record.get("COMMENT_4_BriefSDC1") : null;
//    //this.comment4BriefSDC2 = (record.isSet("COMMENT_4_BriefSDC2")) ? record.get("COMMENT_4_BriefSDC2") : null;
//    //this.comment4BriefSDC3 = (record.isSet("COMMENT_4_BriefSDC3")) ? record.get("COMMENT_4_BriefSDC3") : null;
//    this.comment4BriefNotes1 = (record.isSet("COMMENT_4_BRIEF1")) ? record.get("COMMENT_4_BRIEF1") : null;
//    // // Respondent 5
//    //this.comment5RefDate = (record.isSet("COMMENT_5_REFDTE")) ? record.get("COMMENT_5_REFDTE") : null;
//    this.comment5InterviewerNo = (record.isSet("COMMENT_5_INTVNO")) ? record.get("COMMENT_5_INTVNO") : null;
//    //this.comment5BriefSDC1 = (record.isSet("COMMENT_5_BriefSDC1")) ? record.get("COMMENT_5_BriefSDC1") : null;
//    //this.comment5BriefSDC2 = (record.isSet("COMMENT_5_BriefSDC2")) ? record.get("COMMENT_5_BriefSDC2") : null;
//    //this.comment5BriefSDC3 = (record.isSet("COMMENT_5_BriefSDC3")) ? record.get("COMMENT_5_BriefSDC3") : null;
//    this.comment5BriefNotes1 = (record.isSet("COMMENT_5_BRIEF1")) ? record.get("COMMENT_5_BRIEF1") : null;
//    // // // Other
//    this.direction = (record.isSet("DIRECTION")) ? record.get("DIRECTION") : null;
//  }

}

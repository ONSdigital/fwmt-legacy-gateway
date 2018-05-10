package uk.gov.ons.fwmt.gateway.entity.legacy.csv;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

@Data
public class JobLFSDataIngest {
  // taken from the 'Quota_No' field
  // ignored
  //private final String quotaNo;

  // taken from the 'RefDate' field
  private final String refDate;

  // taken from the 'LSTHO' field
  private final String lstho;

  // taken from the 'PREM1' field
  private final String thisWv;

  // taken from the 'MAIN' field
  private final String main;

  // taken from the 'NUMHHLD' field
  private final String numberHouseholds;

  // taken from the 'HHLDDESC' field
  private final String householdsDesc;

  // // // Names

  // taken from the 'QRES_LINE_NAME_1' field
  private final String respondentName1;

  // taken from the 'QRES_LINE_NAME_2' field
  private final String respondentName2;

  // taken from the 'QRES_LINE_NAME_3' field
  private final String respondentName3;

  // taken from the 'QRES_LINE_NAME_4' field
  private final String respondentName4;

  // taken from the 'QRES_LINE_NAME_5' field
  private final String respondentName5;

  // taken from the 'QRES_LINE_NAME_6' field
  private final String respondentName6;

  // taken from the 'QRES_LINE_NAME_7' field
  private final String respondentName7;

  // taken from the 'QRES_LINE_NAME_8' field
  private final String respondentName8;

  // taken from the 'QRES_LINE_NAME_9' field
  private final String respondentName9;

  // taken from the 'QRES_LINE_NAME_10' field
  private final String respondentName10;

  // taken from the 'QRES_LINE_NAME_11' field
  private final String respondentName11;

  // taken from the 'QRES_LINE_NAME_12' field
  private final String respondentName12;

  // taken from the 'QRES_LINE_NAME_13' field
  private final String respondentName13;

  // taken from the 'QRES_LINE_NAME_14' field
  private final String respondentName14;

  // taken from the 'QRES_LINE_NAME_15' field
  private final String respondentName15;

  // taken from the 'QRES_LINE_NAME_16' field
  private final String respondentName16;

  // // // Ages

  // taken from the 'QRES_LINE_AGE_1' field
  private final String respondentAge1;

  // taken from the 'QRES_LINE_AGE_2' field
  private final String respondentAge2;

  // taken from the 'QRES_LINE_AGE_3' field
  private final String respondentAge3;

  // taken from the 'QRES_LINE_AGE_4' field
  private final String respondentAge4;

  // taken from the 'QRES_LINE_AGE_5' field
  private final String respondentAge5;

  // taken from the 'QRES_LINE_AGE_6' field
  private final String respondentAge6;

  // taken from the 'QRES_LINE_AGE_7' field
  private final String respondentAge7;

  // taken from the 'QRES_LINE_AGE_8' field
  private final String respondentAge8;

  // taken from the 'QRES_LINE_AGE_9' field
  private final String respondentAge9;

  // taken from the 'QRES_LINE_AGE_10' field
  private final String respondentAge10;

  // taken from the 'QRES_LINE_AGE_11' field
  private final String respondentAge11;

  // taken from the 'QRES_LINE_AGE_12' field
  private final String respondentAge12;

  // taken from the 'QRES_LINE_AGE_13' field
  private final String respondentAge13;

  // taken from the 'QRES_LINE_AGE_14' field
  private final String respondentAge14;

  // taken from the 'QRES_LINE_AGE_15' field
  private final String respondentAge15;

  // taken from the 'QRES_LINE_AGE_16' field
  private final String respondentAge16;

  // // // Indicators

  // // Respondent 1

  // taken from the 'QINDIV_1_WRKING' field
  private final String respondentWorkIndicator1;

  // taken from the 'QINDIV_1_JBAWAY' field
  // ignored
  //private final String qindiv1Jbaway;

  // taken from the 'QINDIV_1_OWNBUS' field
  // ignored
  //private final String qindiv1Ownbus;

  // taken from the 'QINDIV_1_RELBUS' field
  // ignored
  //private final String qindiv1Relbus;

  // taken from the 'QINDIV_1_LOOK4' field
  private final String respondentLookingForWork1;

  // taken from the 'QINDIV_1_DIFJOB' field
  // ignored
  //private final String qindiv1Difjob;

  // taken from the 'QINDIV_1_INDOUT' field
  private final String respondentInterviewType1;

  // // Respondent 2

  // taken from the 'QINDIV_2_WRKING' field
  private final String respondentWorkIndicator2;

  // taken from the 'QINDIV_2_JBAWAY' field
  // ignored
  //private final String qindiv2Jbaway;

  // taken from the 'QINDIV_2_OWNBUS' field
  // ignored
  //private final String qindiv2Ownbus;

  // taken from the 'QINDIV_2_RELBUS' field
  // ignored
  //private final String qindiv2Relbus;

  // taken from the 'QINDIV_2_LOOK4' field
  private final String respondentLookingForWork2;

  // taken from the 'QINDIV_2_DIFJOB' field
  // ignored
  //private final String qindiv2Difjob;

  // taken from the 'QINDIV_2_INDOUT' field
  private final String respondentInterviewType2;

  // // Respondent 3

  // taken from the 'QINDIV_3_WRKING' field
  private final String respondentWorkIndicator3;

  // taken from the 'QINDIV_3_JBAWAY' field
  // ignored
  //private final String qindiv3Jbaway;

  // taken from the 'QINDIV_3_OWNBUS' field
  // ignored
  //private final String qindiv3Ownbus;

  // taken from the 'QINDIV_3_RELBUS' field
  // ignored
  //private final String qindiv3Relbus;

  // taken from the 'QINDIV_3_LOOK4' field
  private final String respondentLookingForWork3;

  // taken from the 'QINDIV_3_DIFJOB' field
  // ignored
  //private final String qindiv3Difjob;

  // taken from the 'QINDIV_3_INDOUT' field
  private final String respondentInterviewType3;

  // // Respondent 4

  // taken from the 'QINDIV_4_WRKING' field
  private final String respondentWorkIndicator4;

  // taken from the 'QINDIV_4_JBAWAY' field
  // ignored
  //private final String qindiv4Jbaway;

  // taken from the 'QINDIV_4_OWNBUS' field
  // ignored
  //private final String qindiv4Ownbus;

  // taken from the 'QINDIV_4_RELBUS' field
  // ignored
  //private final String qindiv4Relbus;

  // taken from the 'QINDIV_4_LOOK4' field
  private final String respondentLookingForWork4;

  // taken from the 'QINDIV_4_DIFJOB' field
  // ignored
  //private final String qindiv4Difjob;

  // taken from the 'QINDIV_4_INDOUT' field
  private final String respondentInterviewType4;

  // // Respondent 5

  // taken from the 'QINDIV_5_WRKING' field
  private final String respondentWorkIndicator5;

  // taken from the 'QINDIV_5_JBAWAY' field
  // ignored
  //private final String qindiv5Jbaway;

  // taken from the 'QINDIV_5_OWNBUS' field
  // ignored
  //private final String qindiv5Ownbus;

  // taken from the 'QINDIV_5_RELBUS' field
  // ignored
  //private final String qindiv5Relbus;

  // taken from the 'QINDIV_5_LOOK4' field
  private final String respondentLookingForWork5;

  // taken from the 'QINDIV_5_DIFJOB' field
  // ignored
  //private final String qindiv5Difjob;

  // taken from the 'QINDIV_5_INDOUT' field
  private final String respondentInterviewType5;

  // // Respondent 6

  // taken from the 'QINDIV_6_WRKING' field
  private final String respondentWorkIndicator6;

  // taken from the 'QINDIV_6_JBAWAY' field
  // ignored
  //private final String qindiv6Jbaway;

  // taken from the 'QINDIV_6_OWNBUS' field
  // ignored
  //private final String qindiv6Ownbus;

  // taken from the 'QINDIV_6_RELBUS' field
  // ignored
  //private final String qindiv6Relbus;

  // taken from the 'QINDIV_6_LOOK4' field
  private final String respondentLookingForWork6;

  // taken from the 'QINDIV_6_DIFJOB' field
  // ignored
  //private final String qindiv6Difjob;

  // taken from the 'QINDIV_6_INDOUT' field
  private final String respondentInterviewType6;

  // // Respondent 7

  // taken from the 'QINDIV_7_WRKING' field
  private final String respondentWorkIndicator7;

  // taken from the 'QINDIV_7_JBAWAY' field
  // ignored
  //private final String qindiv7Jbaway;

  // taken from the 'QINDIV_7_OWNBUS' field
  // ignored
  //private final String qindiv7Ownbus;

  // taken from the 'QINDIV_7_RELBUS' field
  // ignored
  //private final String qindiv7Relbus;

  // taken from the 'QINDIV_7_LOOK4' field
  private final String respondentLookingForWork7;

  // taken from the 'QINDIV_7_DIFJOB' field
  // ignored
  //private final String qindiv7Difjob;

  // taken from the 'QINDIV_7_INDOUT' field
  private final String respondentInterviewType7;

  // // Respondent 8

  // taken from the 'QINDIV_8_WRKING' field
  private final String respondentWorkIndicator8;

  // taken from the 'QINDIV_8_JBAWAY' field
  // ignored
  //private final String qindiv8Jbaway;

  // taken from the 'QINDIV_8_OWNBUS' field
  // ignored
  //private final String qindiv8Ownbus;

  // taken from the 'QINDIV_8_RELBUS' field
  // ignored
  //private final String qindiv8Relbus;

  // taken from the 'QINDIV_8_LOOK4' field
  private final String respondentLookingForWork8;

  // taken from the 'QINDIV_8_DIFJOB' field
  // ignored
  //private final String qindiv8Difjob;

  // taken from the 'QINDIV_8_INDOUT' field
  private final String respondentInterviewType8;

  // // Respondent 9

  // taken from the 'QINDIV_9_WRKING' field
  private final String respondentWorkIndicator9;

  // taken from the 'QINDIV_9_JBAWAY' field
  // ignored
  //private final String qindiv9Jbaway;

  // taken from the 'QINDIV_9_OWNBUS' field
  // ignored
  //private final String qindiv9Ownbus;

  // taken from the 'QINDIV_9_RELBUS' field
  // ignored
  //private final String qindiv9Relbus;

  // taken from the 'QINDIV_9_LOOK4' field
  private final String respondentLookingForWork9;

  // taken from the 'QINDIV_9_DIFJOB' field
  // ignored
  //private final String qindiv9Difjob;

  // taken from the 'QINDIV_9_INDOUT' field
  private final String respondentInterviewType9;

  // // Respondent 10

  // taken from the 'QINDIV_10_WRKING' field
  private final String respondentWorkIndicator10;

  // taken from the 'QINDIV_10_JBAWAY' field
  // ignored
  //private final String qindiv10Jbaway;

  // taken from the 'QINDIV_10_OWNBUS' field
  // ignored
  //private final String qindiv10Ownbus;

  // taken from the 'QINDIV_10_RELBUS' field
  // ignored
  //private final String qindiv10Relbus;

  // taken from the 'QINDIV_10_LOOK4' field
  private final String respondentLookingForWork10;

  // taken from the 'QINDIV_10_DIFJOB' field
  // ignored
  //private final String qindiv10Difjob;

  // taken from the 'QINDIV_10_INDOUT' field
  private final String respondentInterviewType10;

  // // Respondent 11

  // taken from the 'QINDIV_11_WRKING' field
  private final String respondentWorkIndicator11;

  // taken from the 'QINDIV_11_JBAWAY' field
  // ignored
  //private final String qindiv11Jbaway;

  // taken from the 'QINDIV_11_OWNBUS' field
  // ignored
  //private final String qindiv11Ownbus;

  // taken from the 'QINDIV_11_RELBUS' field
  // ignored
  //private final String qindiv11Relbus;

  // taken from the 'QINDIV_11_LOOK4' field
  private final String respondentLookingForWork11;

  // taken from the 'QINDIV_11_DIFJOB' field
  // ignored
  //private final String qindiv11Difjob;

  // taken from the 'QINDIV_11_INDOUT' field
  private final String respondentInterviewType11;

  // // Respondent 12

  // taken from the 'QINDIV_12_WRKING' field
  private final String respondentWorkIndicator12;

  // taken from the 'QINDIV_12_JBAWAY' field
  // ignored
  //private final String qindiv12Jbaway;

  // taken from the 'QINDIV_12_OWNBUS' field
  // ignored
  //private final String qindiv12Ownbus;

  // taken from the 'QINDIV_12_RELBUS' field
  // ignored
  //private final String qindiv12Relbus;

  // taken from the 'QINDIV_12_LOOK4' field
  private final String respondentLookingForWork12;

  // taken from the 'QINDIV_12_DIFJOB' field
  // ignored
  //private final String qindiv12Difjob;

  // taken from the 'QINDIV_12_INDOUT' field
  private final String respondentInterviewType12;

  // // Respondent 13

  // taken from the 'QINDIV_13_WRKING' field
  private final String respondentWorkIndicator13;

  // taken from the 'QINDIV_13_JBAWAY' field
  // ignored
  //private final String qindiv13Jbaway;

  // taken from the 'QINDIV_13_OWNBUS' field
  // ignored
  //private final String qindiv13Ownbus;

  // taken from the 'QINDIV_13_RELBUS' field
  // ignored
  //private final String qindiv13Relbus;

  // taken from the 'QINDIV_13_LOOK4' field
  private final String respondentLookingForWork13;

  // taken from the 'QINDIV_13_DIFJOB' field
  // ignored
  //private final String qindiv13Difjob;

  // taken from the 'QINDIV_13_INDOUT' field
  private final String respondentInterviewType13;

  // // Respondent 14

  // taken from the 'QINDIV_14_WRKING' field
  private final String respondentWorkIndicator14;

  // taken from the 'QINDIV_14_JBAWAY' field
  // ignored
  //private final String qindiv14Jbaway;

  // taken from the 'QINDIV_14_OWNBUS' field
  // ignored
  //private final String qindiv14Ownbus;

  // taken from the 'QINDIV_14_RELBUS' field
  // ignored
  //private final String qindiv14Relbus;

  // taken from the 'QINDIV_14_LOOK4' field
  private final String respondentLookingForWork14;

  // taken from the 'QINDIV_14_DIFJOB' field
  // ignored
  //private final String qindiv14Difjob;

  // taken from the 'QINDIV_14_INDOUT' field
  private final String respondentInterviewType14;

  // // Respondent 15

  // taken from the 'QINDIV_15_WRKING' field
  private final String respondentWorkIndicator15;

  // taken from the 'QINDIV_15_JBAWAY' field
  // ignored
  //private final String qindiv15Jbaway;

  // taken from the 'QINDIV_15_OWNBUS' field
  // ignored
  //private final String qindiv15Ownbus;

  // taken from the 'QINDIV_15_RELBUS' field
  // ignored
  //private final String qindiv15Relbus;

  // taken from the 'QINDIV_15_LOOK4' field
  private final String respondentLookingForWork15;

  // taken from the 'QINDIV_15_DIFJOB' field
  // ignored
  //private final String qindiv15Difjob;

  // taken from the 'QINDIV_15_INDOUT' field
  private final String respondentInterviewType15;

  // // Respondent 16

  // taken from the 'QINDIV_16_WRKING' field
  private final String respondentWorkIndicator16;

  // taken from the 'QINDIV_16_JBAWAY' field
  // ignored
  //private final String qindiv16Jbaway;

  // taken from the 'QINDIV_16_OWNBUS' field
  // ignored
  //private final String qindiv16Ownbus;

  // taken from the 'QINDIV_16_RELBUS' field
  // ignored
  //private final String qindiv16Relbus;

  // taken from the 'QINDIV_16_LOOK4' field
  private final String respondentLookingForWork16;

  // taken from the 'QINDIV_16_DIFJOB' field
  // ignored
  //private final String qindiv16Difjob;

  // taken from the 'QINDIV_16_INDOUT' field
  private final String respondentInterviewType16;

  // // // Notes?

  // taken from the 'tba' field
  // TODO is this just a mis-spelling?
  // ignored
  //private final String briefNotes;

  // // // Other

  // taken from the 'WEEK' field
  // ignored
  //private final String week;

  // taken from the 'W1YR' field
  // ignored
  //private final String w1yr;

  // taken from the 'QRTR' field
  // ignored
  //private final String qrtr;

  // taken from the 'WAVFND' field
  // ignored
  //private final String wavfnd;

  // taken from the 'HHLD' field
  // ignored
  //private final String hhld;

  // taken from the 'CHKLET' field
  // ignored
  //private final String chklet;

  // taken from the 'DIVADDIND' field
  // ignored
  //private final String divAddInd;

  // taken from the 'MO' field
  // ignored
  //private final String mo;

  // taken from the 'HOUT' field
  // ignored
  //private final String hOut;

  // taken from the 'LSTHO' field
  // ignored
  //private final String lstho;

  // taken from the 'LFSSAMP' field
  // ignored
  //private final String lfsSamp;

  // taken from the 'THANKS' field
  // ignored
  //private final String thanks;

  // taken from the 'THANKE' field
  // ignored
  //private final String thanke;

  // taken from the 'RECPHONE' field
  // ignored
  //private final String recPhone;

  // taken from the 'COUNTRY' field
  // ignored
  //private final String country;

  // taken from the 'NUMPER' field
  // ignored
  //private final String numper;

  // taken from the 'NUMPER' field
  // ignored
  //private final String numper;

  // // // Comments

  // // Respondent 1

  // taken from the 'COMMENT_1_REFDTE' field
  // ignored
  //private final String comment1RefDate;

  // taken from the 'COMMENT_1_INTVNO' field
  private final String comment1InterviewerNo;

  // taken from the 'COMMENT_1_BriefSDC1' field
  // ignored
  //private final String comment1BriefSDC1;

  // taken from the 'COMMENT_1_BriefSDC2' field
  // ignored
  //private final String comment1BriefSDC2;

  // taken from the 'COMMENT_1_BriefSDC3' field
  // ignored
  //private final String comment1BriefSDC3;

  // taken from the 'COMMENT_1_BRIEF1' field
  private final String comment1BriefNotes1;

  // // Respondent 2

  // taken from the 'COMMENT_2_REFDTE' field
  // ignored
  //private final String comment2RefDate;

  // taken from the 'COMMENT_2_INTVNO' field
  private final String comment2InterviewerNo;

  // taken from the 'COMMENT_2_BriefSDC1' field
  // ignored
  //private final String comment2BriefSDC1;

  // taken from the 'COMMENT_2_BriefSDC2' field
  // ignored
  //private final String comment2BriefSDC2;

  // taken from the 'COMMENT_2_BriefSDC3' field
  // ignored
  //private final String comment2BriefSDC3;

  // taken from the 'COMMENT_2_BRIEF1' field
  private final String comment2BriefNotes1;

  // // Respondent 3

  // taken from the 'COMMENT_3_REFDTE' field
  // ignored
  //private final String comment3RefDate;

  // taken from the 'COMMENT_3_INTVNO' field
  private final String comment3InterviewerNo;

  // taken from the 'COMMENT_3_BriefSDC1' field
  // ignored
  //private final String comment3BriefSDC1;

  // taken from the 'COMMENT_3_BriefSDC2' field
  // ignored
  //private final String comment3BriefSDC2;

  // taken from the 'COMMENT_3_BriefSDC3' field
  // ignored
  //private final String comment3BriefSDC3;

  // taken from the 'COMMENT_3_BRIEF1' field
  private final String comment3BriefNotes1;

  // // Respondent 4

  // taken from the 'COMMENT_4_REFDTE' field
  // ignored
  //private final String comment4RefDate;

  // taken from the 'COMMENT_4_INTVNO' field
  private final String comment4InterviewerNo;

  // taken from the 'COMMENT_4_BriefSDC1' field
  // ignored
  //private final String comment4BriefSDC1;

  // taken from the 'COMMENT_4_BriefSDC2' field
  // ignored
  //private final String comment4BriefSDC2;

  // taken from the 'COMMENT_4_BriefSDC3' field
  // ignored
  //private final String comment4BriefSDC3;

  // taken from the 'COMMENT_4_BRIEF1' field
  private final String comment4BriefNotes1;

  // // Respondent 5

  // taken from the 'COMMENT_5_REFDTE' field
  // ignored
  //private final String comment5RefDate;

  // taken from the 'COMMENT_5_INTVNO' field
  private final String comment5InterviewerNo;

  // taken from the 'COMMENT_5_BriefSDC1' field
  // ignored
  //private final String comment5BriefSDC1;

  // taken from the 'COMMENT_5_BriefSDC2' field
  // ignored
  //private final String comment5BriefSDC2;

  // taken from the 'COMMENT_5_BriefSDC3' field
  // ignored
  //private final String comment5BriefSDC3;

  // taken from the 'COMMENT_5_BRIEF1' field
  private final String comment5BriefNotes1;

  // // // Other

  // taken from the 'DIRECTION' field
  private final String direction;



  public JobLFSDataIngest(CSVRecord record) {
    //this.quotaNo = record.get("Quota_No");
    this.refDate = record.get("RefDate");
    this.lstho = record.get("LSTHO");
    this.thisWv = record.get("PREM1");
    this.main = record.get("MAIN");
    this.numberHouseholds = record.get("NUMHHLD");
    this.householdsDesc = record.get("HHLDDESC");
    // // // Names
    this.respondentName1 = record.get("QRES_LINE_NAME_1");
    this.respondentName2 = record.get("QRES_LINE_NAME_2");
    this.respondentName3 = record.get("QRES_LINE_NAME_3");
    this.respondentName4 = record.get("QRES_LINE_NAME_4");
    this.respondentName5 = record.get("QRES_LINE_NAME_5");
    this.respondentName6 = record.get("QRES_LINE_NAME_6");
    this.respondentName7 = record.get("QRES_LINE_NAME_7");
    this.respondentName8 = record.get("QRES_LINE_NAME_8");
    this.respondentName9 = record.get("QRES_LINE_NAME_9");
    this.respondentName10 = record.get("QRES_LINE_NAME_10");
    this.respondentName11 = record.get("QRES_LINE_NAME_11");
    this.respondentName12 = record.get("QRES_LINE_NAME_12");
    this.respondentName13 = record.get("QRES_LINE_NAME_13");
    this.respondentName14 = record.get("QRES_LINE_NAME_14");
    this.respondentName15 = record.get("QRES_LINE_NAME_15");
    this.respondentName16 = record.get("QRES_LINE_NAME_16");
    // // // Ages
    this.respondentAge1 = record.get("QRES_LINE_AGE_1");
    this.respondentAge2 = record.get("QRES_LINE_AGE_2");
    this.respondentAge3 = record.get("QRES_LINE_AGE_3");
    this.respondentAge4 = record.get("QRES_LINE_AGE_4");
    this.respondentAge5 = record.get("QRES_LINE_AGE_5");
    this.respondentAge6 = record.get("QRES_LINE_AGE_6");
    this.respondentAge7 = record.get("QRES_LINE_AGE_7");
    this.respondentAge8 = record.get("QRES_LINE_AGE_8");
    this.respondentAge9 = record.get("QRES_LINE_AGE_9");
    this.respondentAge10 = record.get("QRES_LINE_AGE_10");
    this.respondentAge11 = record.get("QRES_LINE_AGE_11");
    this.respondentAge12 = record.get("QRES_LINE_AGE_12");
    this.respondentAge13 = record.get("QRES_LINE_AGE_13");
    this.respondentAge14 = record.get("QRES_LINE_AGE_14");
    this.respondentAge15 = record.get("QRES_LINE_AGE_15");
    this.respondentAge16 = record.get("QRES_LINE_AGE_16");
    // // // Indicators
    // // Respondent 1
    this.respondentWorkIndicator1 = record.get("QINDIV_1_WRKING");
    //this.qindiv1Jbaway = record.get("QINDIV_1_JBAWAY");
    //this.qindiv1Ownbus = record.get("QINDIV_1_OWNBUS");
    //this.qindiv1Relbus = record.get("QINDIV_1_RELBUS");
    this.respondentLookingForWork1 = record.get("QINDIV_1_LOOK4");
    //this.qindiv1Difjob = record.get("QINDIV_1_DIFJOB");
    this.respondentInterviewType1 = record.get("QINDIV_1_INDOUT");
    // // Respondent 2
    this.respondentWorkIndicator2 = record.get("QINDIV_2_WRKING");
    //this.qindiv2Jbaway = record.get("QINDIV_2_JBAWAY");
    //this.qindiv2Ownbus = record.get("QINDIV_2_OWNBUS");
    //this.qindiv2Relbus = record.get("QINDIV_2_RELBUS");
    this.respondentLookingForWork2 = record.get("QINDIV_2_LOOK4");
    //this.qindiv2Difjob = record.get("QINDIV_2_DIFJOB");
    this.respondentInterviewType2 = record.get("QINDIV_2_INDOUT");
    // // Respondent 3
    this.respondentWorkIndicator3 = record.get("QINDIV_3_WRKING");
    //this.qindiv3Jbaway = record.get("QINDIV_3_JBAWAY");
    //this.qindiv3Ownbus = record.get("QINDIV_3_OWNBUS");
    //this.qindiv3Relbus = record.get("QINDIV_3_RELBUS");
    this.respondentLookingForWork3 = record.get("QINDIV_3_LOOK4");
    //this.qindiv3Difjob = record.get("QINDIV_3_DIFJOB");
    this.respondentInterviewType3 = record.get("QINDIV_3_INDOUT");
    // // Respondent 4
    this.respondentWorkIndicator4 = record.get("QINDIV_4_WRKING");
    //this.qindiv4Jbaway = record.get("QINDIV_4_JBAWAY");
    //this.qindiv4Ownbus = record.get("QINDIV_4_OWNBUS");
    //this.qindiv4Relbus = record.get("QINDIV_4_RELBUS");
    this.respondentLookingForWork4 = record.get("QINDIV_4_LOOK4");
    //this.qindiv4Difjob = record.get("QINDIV_4_DIFJOB");
    this.respondentInterviewType4 = record.get("QINDIV_4_INDOUT");
    // // Respondent 5
    this.respondentWorkIndicator5 = record.get("QINDIV_5_WRKING");
    //this.qindiv5Jbaway = record.get("QINDIV_5_JBAWAY");
    //this.qindiv5Ownbus = record.get("QINDIV_5_OWNBUS");
    //this.qindiv5Relbus = record.get("QINDIV_5_RELBUS");
    this.respondentLookingForWork5 = record.get("QINDIV_5_LOOK4");
    //this.qindiv5Difjob = record.get("QINDIV_5_DIFJOB");
    this.respondentInterviewType5 = record.get("QINDIV_5_INDOUT");
    // // Respondent 6
    this.respondentWorkIndicator6 = record.get("QINDIV_6_WRKING");
    //this.qindiv6Jbaway = record.get("QINDIV_6_JBAWAY");
    //this.qindiv6Ownbus = record.get("QINDIV_6_OWNBUS");
    //this.qindiv6Relbus = record.get("QINDIV_6_RELBUS");
    this.respondentLookingForWork6 = record.get("QINDIV_6_LOOK4");
    //this.qindiv6Difjob = record.get("QINDIV_6_DIFJOB");
    this.respondentInterviewType6 = record.get("QINDIV_6_INDOUT");
    // // Respondent 7
    this.respondentWorkIndicator7 = record.get("QINDIV_7_WRKING");
    //this.qindiv7Jbaway = record.get("QINDIV_7_JBAWAY");
    //this.qindiv7Ownbus = record.get("QINDIV_7_OWNBUS");
    //this.qindiv7Relbus = record.get("QINDIV_7_RELBUS");
    this.respondentLookingForWork7 = record.get("QINDIV_7_LOOK4");
    //this.qindiv7Difjob = record.get("QINDIV_7_DIFJOB");
    this.respondentInterviewType7 = record.get("QINDIV_7_INDOUT");
    // // Respondent 8
    this.respondentWorkIndicator8 = record.get("QINDIV_8_WRKING");
    //this.qindiv8Jbaway = record.get("QINDIV_8_JBAWAY");
    //this.qindiv8Ownbus = record.get("QINDIV_8_OWNBUS");
    //this.qindiv8Relbus = record.get("QINDIV_8_RELBUS");
    this.respondentLookingForWork8 = record.get("QINDIV_8_LOOK4");
    //this.qindiv8Difjob = record.get("QINDIV_8_DIFJOB");
    this.respondentInterviewType8 = record.get("QINDIV_8_INDOUT");
    // // Respondent 9
    this.respondentWorkIndicator9 = record.get("QINDIV_9_WRKING");
    //this.qindiv9Jbaway = record.get("QINDIV_9_JBAWAY");
    //this.qindiv9Ownbus = record.get("QINDIV_9_OWNBUS");
    //this.qindiv9Relbus = record.get("QINDIV_9_RELBUS");
    this.respondentLookingForWork9 = record.get("QINDIV_9_LOOK4");
    //this.qindiv9Difjob = record.get("QINDIV_9_DIFJOB");
    this.respondentInterviewType9 = record.get("QINDIV_9_INDOUT");
    // // Respondent 10
    this.respondentWorkIndicator10 = record.get("QINDIV_10_WRKING");
    //this.qindiv10Jbaway = record.get("QINDIV_10_JBAWAY");
    //this.qindiv10Ownbus = record.get("QINDIV_10_OWNBUS");
    //this.qindiv10Relbus = record.get("QINDIV_10_RELBUS");
    this.respondentLookingForWork10 = record.get("QINDIV_10_LOOK4");
    //this.qindiv10Difjob = record.get("QINDIV_10_DIFJOB");
    this.respondentInterviewType10 = record.get("QINDIV_10_INDOUT");
    // // Respondent 11
    this.respondentWorkIndicator11 = record.get("QINDIV_11_WRKING");
    //this.qindiv11Jbaway = record.get("QINDIV_11_JBAWAY");
    //this.qindiv11Ownbus = record.get("QINDIV_11_OWNBUS");
    //this.qindiv11Relbus = record.get("QINDIV_11_RELBUS");
    this.respondentLookingForWork11 = record.get("QINDIV_11_LOOK4");
    //this.qindiv11Difjob = record.get("QINDIV_11_DIFJOB");
    this.respondentInterviewType11 = record.get("QINDIV_11_INDOUT");
    // // Respondent 12
    this.respondentWorkIndicator12 = record.get("QINDIV_12_WRKING");
    //this.qindiv12Jbaway = record.get("QINDIV_12_JBAWAY");
    //this.qindiv12Ownbus = record.get("QINDIV_12_OWNBUS");
    //this.qindiv12Relbus = record.get("QINDIV_12_RELBUS");
    this.respondentLookingForWork12 = record.get("QINDIV_12_LOOK4");
    //this.qindiv12Difjob = record.get("QINDIV_12_DIFJOB");
    this.respondentInterviewType12 = record.get("QINDIV_12_INDOUT");
    // // Respondent 13
    this.respondentWorkIndicator13 = record.get("QINDIV_13_WRKING");
    //this.qindiv13Jbaway = record.get("QINDIV_13_JBAWAY");
    //this.qindiv13Ownbus = record.get("QINDIV_13_OWNBUS");
    //this.qindiv13Relbus = record.get("QINDIV_13_RELBUS");
    this.respondentLookingForWork13 = record.get("QINDIV_13_LOOK4");
    //this.qindiv13Difjob = record.get("QINDIV_13_DIFJOB");
    this.respondentInterviewType13 = record.get("QINDIV_13_INDOUT");
    // // Respondent 14
    this.respondentWorkIndicator14 = record.get("QINDIV_14_WRKING");
    //this.qindiv14Jbaway = record.get("QINDIV_14_JBAWAY");
    //this.qindiv14Ownbus = record.get("QINDIV_14_OWNBUS");
    //this.qindiv14Relbus = record.get("QINDIV_14_RELBUS");
    this.respondentLookingForWork14 = record.get("QINDIV_14_LOOK4");
    //this.qindiv14Difjob = record.get("QINDIV_14_DIFJOB");
    this.respondentInterviewType14 = record.get("QINDIV_14_INDOUT");
    // // Respondent 15
    this.respondentWorkIndicator15 = record.get("QINDIV_15_WRKING");
    //this.qindiv15Jbaway = record.get("QINDIV_15_JBAWAY");
    //this.qindiv15Ownbus = record.get("QINDIV_15_OWNBUS");
    //this.qindiv15Relbus = record.get("QINDIV_15_RELBUS");
    this.respondentLookingForWork15 = record.get("QINDIV_15_LOOK4");
    //this.qindiv15Difjob = record.get("QINDIV_15_DIFJOB");
    this.respondentInterviewType15 = record.get("QINDIV_15_INDOUT");
    // // Respondent 16
    this.respondentWorkIndicator16 = record.get("QINDIV_16_WRKING");
    //this.qindiv16Jbaway = record.get("QINDIV_16_JBAWAY");
    //this.qindiv16Ownbus = record.get("QINDIV_16_OWNBUS");
    //this.qindiv16Relbus = record.get("QINDIV_16_RELBUS");
    this.respondentLookingForWork16 = record.get("QINDIV_16_LOOK4");
    //this.qindiv16Difjob = record.get("QINDIV_16_DIFJOB");
    this.respondentInterviewType16 = record.get("QINDIV_16_INDOUT");
    // // // Notes?
    //this.briefNotes = record.get("tba");
    // // // Other
    //this.week = record.get("WEEK");
    //this.w1yr = record.get("W1YR");
    //this.qrtr = record.get("QRTR");
    //this.wavfnd = record.get("WAVFND");
    //this.hhld = record.get("HHLD");
    //this.chklet = record.get("CHKLET");
    //this.divAddInd = record.get("DIVADDIND");
    //this.mo = record.get("MO");
    //this.hOut = record.get("HOUT");
    //this.lstho = record.get("LSTHO");
    //this.lfsSamp = record.get("LFSSAMP");
    //this.thanks = record.get("THANKS");
    //this.thanke = record.get("THANKE");
    //this.recPhone = record.get("RECPHONE");
    //this.country = record.get("COUNTRY");
    //this.numper = record.get("NUMPER");
    //this.numper = record.get("NUMPER");
    // // // Comments
    // // Respondent 1
    //this.comment1RefDate = record.get("COMMENT_1_REFDTE");
    this.comment1InterviewerNo = record.get("COMMENT_1_INTVNO");
    //this.comment1BriefSDC1 = record.get("COMMENT_1_BriefSDC1");
    //this.comment1BriefSDC2 = record.get("COMMENT_1_BriefSDC2");
    //this.comment1BriefSDC3 = record.get("COMMENT_1_BriefSDC3");
    this.comment1BriefNotes1 = record.get("COMMENT_1_BRIEF1");
    // // Respondent 2
    //this.comment2RefDate = record.get("COMMENT_2_REFDTE");
    this.comment2InterviewerNo = record.get("COMMENT_2_INTVNO");
    //this.comment2BriefSDC1 = record.get("COMMENT_2_BriefSDC1");
    //this.comment2BriefSDC2 = record.get("COMMENT_2_BriefSDC2");
    //this.comment2BriefSDC3 = record.get("COMMENT_2_BriefSDC3");
    this.comment2BriefNotes1 = record.get("COMMENT_2_BRIEF1");
    // // Respondent 3
    //this.comment3RefDate = record.get("COMMENT_3_REFDTE");
    this.comment3InterviewerNo = record.get("COMMENT_3_INTVNO");
    //this.comment3BriefSDC1 = record.get("COMMENT_3_BriefSDC1");
    //this.comment3BriefSDC2 = record.get("COMMENT_3_BriefSDC2");
    //this.comment3BriefSDC3 = record.get("COMMENT_3_BriefSDC3");
    this.comment3BriefNotes1 = record.get("COMMENT_3_BRIEF1");
    // // Respondent 4
    //this.comment4RefDate = record.get("COMMENT_4_REFDTE");
    this.comment4InterviewerNo = record.get("COMMENT_4_INTVNO");
    //this.comment4BriefSDC1 = record.get("COMMENT_4_BriefSDC1");
    //this.comment4BriefSDC2 = record.get("COMMENT_4_BriefSDC2");
    //this.comment4BriefSDC3 = record.get("COMMENT_4_BriefSDC3");
    this.comment4BriefNotes1 = record.get("COMMENT_4_BRIEF1");
    // // Respondent 5
    //this.comment5RefDate = record.get("COMMENT_5_REFDTE");
    this.comment5InterviewerNo = record.get("COMMENT_5_INTVNO");
    //this.comment5BriefSDC1 = record.get("COMMENT_5_BriefSDC1");
    //this.comment5BriefSDC2 = record.get("COMMENT_5_BriefSDC2");
    //this.comment5BriefSDC3 = record.get("COMMENT_5_BriefSDC3");
    this.comment5BriefNotes1 = record.get("COMMENT_5_BRIEF1");
    // // // Other
    this.direction = record.get("DIRECTION");
  }

}

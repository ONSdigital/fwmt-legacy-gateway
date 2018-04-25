package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;

@Slf4j
public class LegacyLFSSampleReader {
  static final List<String> CSV_HEADERS;

  static final List<String> DATA_FIELDS;

  static final Map<String, String> SAMPLE_LFS_DATA_COLUMN_MAP;

  static {
    List<String> csvHeaders = new ArrayList<>();
    List<String> dataFields = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    BiConsumer<String, String> register = (csvHeader, dataField) -> {
      csvHeaders.add(csvHeader);
      dataFields.add(dataField);
      map.put(csvHeader, dataField);
    };
    register.accept("SERNO", "serno");
    register.accept("TLA", "tla");
    register.accept("FP", "fp");
    register.accept("Quota_No", "quotaNo");
    register.accept("RefDate", "refDate");
    register.accept("LSTHO", "lstho");
    register.accept("THISWV", "thiswv");
    register.accept("PREM1", "prem1");
    register.accept("PREM2", "prem2");
    register.accept("PREM3", "prem3");
    register.accept("PREM4", "prem4");
    register.accept("DISTRICT", "district");
    register.accept("POSTTOWN", "posttown");
    register.accept("POSTCODE", "postcode");
    register.accept("QUOTA", "quota");
    register.accept("ADDR", "addr");
    register.accept("OSGRIDREF", "osgridref");
    register.accept("Year", "year");
    register.accept("Month", "month");
    register.accept("MAIN", "main");
    register.accept("NUMHHLD", "numhhld");
    register.accept("HHLDDESC", "hhlddesc");
    // respondents
    register.accept("QRES_LINE_NAME_1", "qresLineName1");
    register.accept("QRES_LINE_NAME_2", "qresLineName2");
    register.accept("QRES_LINE_NAME_3", "qresLineName3");
    register.accept("QRES_LINE_NAME_4", "qresLineName4");
    register.accept("QRES_LINE_NAME_5", "qresLineName5");
    register.accept("QRES_LINE_NAME_6", "qresLineName6");
    register.accept("QRES_LINE_NAME_7", "qresLineName7");
    register.accept("QRES_LINE_NAME_8", "qresLineName8");
    register.accept("QRES_LINE_NAME_9", "qresLineName9");
    register.accept("QRES_LINE_NAME_10", "qresLineName10");
    register.accept("QRES_LINE_NAME_11", "qresLineName11");
    register.accept("QRES_LINE_NAME_12", "qresLineName12");
    register.accept("QRES_LINE_NAME_13", "qresLineName13");
    register.accept("QRES_LINE_NAME_14", "qresLineName14");
    register.accept("QRES_LINE_NAME_15", "qresLineName15");
    register.accept("QRES_LINE_NAME_16", "qresLineName16");
    register.accept("QRES_LINE_AGE_1", "qresLineAge1");
    register.accept("QRES_LINE_AGE_2", "qresLineAge2");
    register.accept("QRES_LINE_AGE_3", "qresLineAge3");
    register.accept("QRES_LINE_AGE_4", "qresLineAge4");
    register.accept("QRES_LINE_AGE_5", "qresLineAge5");
    register.accept("QRES_LINE_AGE_6", "qresLineAge6");
    register.accept("QRES_LINE_AGE_7", "qresLineAge7");
    register.accept("QRES_LINE_AGE_8", "qresLineAge8");
    register.accept("QRES_LINE_AGE_9", "qresLineAge9");
    register.accept("QRES_LINE_AGE_10", "qresLineAge10");
    register.accept("QRES_LINE_AGE_11", "qresLineAge11");
    register.accept("QRES_LINE_AGE_12", "qresLineAge12");
    register.accept("QRES_LINE_AGE_13", "qresLineAge13");
    register.accept("QRES_LINE_AGE_14", "qresLineAge14");
    register.accept("QRES_LINE_AGE_15", "qresLineAge15");
    register.accept("QRES_LINE_AGE_16", "qresLineAge16");
    register.accept("QINDIV_1_WRKING", "qindiv1Wrking");
    register.accept("QINDIV_1_JBAWAY", "qindiv1Jbaway");
    register.accept("QINDIV_1_OWNBUS", "qindiv1Ownbus");
    register.accept("QINDIV_1_RELBUS", "qindiv1Relbus");
    register.accept("QINDIV_1_LOOK4", "qindiv1Look4");
    register.accept("QINDIV_1_DIFJOB", "qindiv1Difjob");
    register.accept("QINDIV_1_INDOUT", "qindiv1Indout");
    register.accept("QINDIV_2_WRKING", "qindiv2Wrking");
    register.accept("QINDIV_2_JBAWAY", "qindiv2Jbaway");
    register.accept("QINDIV_2_OWNBUS", "qindiv2Ownbus");
    register.accept("QINDIV_2_RELBUS", "qindiv2Relbus");
    register.accept("QINDIV_2_LOOK4", "qindiv2Look4");
    register.accept("QINDIV_2_DIFJOB", "qindiv2Difjob");
    register.accept("QINDIV_2_INDOUT", "qindiv2Indout");
    register.accept("QINDIV_3_WRKING", "qindiv3Wrking");
    register.accept("QINDIV_3_JBAWAY", "qindiv3Jbaway");
    register.accept("QINDIV_3_OWNBUS", "qindiv3Ownbus");
    register.accept("QINDIV_3_RELBUS", "qindiv3Relbus");
    register.accept("QINDIV_3_LOOK4", "qindiv3Look4");
    register.accept("QINDIV_3_DIFJOB", "qindiv3Difjob");
    register.accept("QINDIV_3_INDOUT", "qindiv3Indout");
    register.accept("QINDIV_4_WRKING", "qindiv4Wrking");
    register.accept("QINDIV_4_JBAWAY", "qindiv4Jbaway");
    register.accept("QINDIV_4_OWNBUS", "qindiv4Ownbus");
    register.accept("QINDIV_4_RELBUS", "qindiv4Relbus");
    register.accept("QINDIV_4_LOOK4", "qindiv4Look4");
    register.accept("QINDIV_4_DIFJOB", "qindiv4Difjob");
    register.accept("QINDIV_4_INDOUT", "qindiv4Indout");
    register.accept("QINDIV_5_WRKING", "qindiv5Wrking");
    register.accept("QINDIV_5_JBAWAY", "qindiv5Jbaway");
    register.accept("QINDIV_5_OWNBUS", "qindiv5Ownbus");
    register.accept("QINDIV_5_RELBUS", "qindiv5Relbus");
    register.accept("QINDIV_5_LOOK4", "qindiv5Look4");
    register.accept("QINDIV_5_DIFJOB", "qindiv5Difjob");
    register.accept("QINDIV_5_INDOUT", "qindiv5Indout");
    register.accept("QINDIV_6_WRKING", "qindiv6Wrking");
    register.accept("QINDIV_6_JBAWAY", "qindiv6Jbaway");
    register.accept("QINDIV_6_OWNBUS", "qindiv6Ownbus");
    register.accept("QINDIV_6_RELBUS", "qindiv6Relbus");
    register.accept("QINDIV_6_LOOK4", "qindiv6Look4");
    register.accept("QINDIV_6_DIFJOB", "qindiv6Difjob");
    register.accept("QINDIV_6_INDOUT", "qindiv6Indout");
    register.accept("QINDIV_7_WRKING", "qindiv7Wrking");
    register.accept("QINDIV_7_JBAWAY", "qindiv7Jbaway");
    register.accept("QINDIV_7_OWNBUS", "qindiv7Ownbus");
    register.accept("QINDIV_7_RELBUS", "qindiv7Relbus");
    register.accept("QINDIV_7_LOOK4", "qindiv7Look4");
    register.accept("QINDIV_7_DIFJOB", "qindiv7Difjob");
    register.accept("QINDIV_7_INDOUT", "qindiv7Indout");
    register.accept("QINDIV_8_WRKING", "qindiv8Wrking");
    register.accept("QINDIV_8_JBAWAY", "qindiv8Jbaway");
    register.accept("QINDIV_8_OWNBUS", "qindiv8Ownbus");
    register.accept("QINDIV_8_RELBUS", "qindiv8Relbus");
    register.accept("QINDIV_8_LOOK4", "qindiv8Look4");
    register.accept("QINDIV_8_DIFJOB", "qindiv8Difjob");
    register.accept("QINDIV_8_INDOUT", "qindiv8Indout");
    register.accept("QINDIV_9_WRKING", "qindiv9Wrking");
    register.accept("QINDIV_9_JBAWAY", "qindiv9Jbaway");
    register.accept("QINDIV_9_OWNBUS", "qindiv9Ownbus");
    register.accept("QINDIV_9_RELBUS", "qindiv9Relbus");
    register.accept("QINDIV_9_LOOK4", "qindiv9Look4");
    register.accept("QINDIV_9_DIFJOB", "qindiv9Difjob");
    register.accept("QINDIV_9_INDOUT", "qindiv9Indout");
    register.accept("QINDIV_10_WRKING", "qindiv10Wrking");
    register.accept("QINDIV_10_JBAWAY", "qindiv10Jbaway");
    register.accept("QINDIV_10_OWNBUS", "qindiv10Ownbus");
    register.accept("QINDIV_10_RELBUS", "qindiv10Relbus");
    register.accept("QINDIV_10_LOOK4", "qindiv10Look4");
    register.accept("QINDIV_10_DIFJOB", "qindiv10Difjob");
    register.accept("QINDIV_10_INDOUT", "qindiv10Indout");
    register.accept("QINDIV_11_WRKING", "qindiv11Wrking");
    register.accept("QINDIV_11_JBAWAY", "qindiv11Jbaway");
    register.accept("QINDIV_11_OWNBUS", "qindiv11Ownbus");
    register.accept("QINDIV_11_RELBUS", "qindiv11Relbus");
    register.accept("QINDIV_11_LOOK4", "qindiv11Look4");
    register.accept("QINDIV_11_DIFJOB", "qindiv11Difjob");
    register.accept("QINDIV_11_INDOUT", "qindiv11Indout");
    register.accept("QINDIV_12_WRKING", "qindiv12Wrking");
    register.accept("QINDIV_12_JBAWAY", "qindiv12Jbaway");
    register.accept("QINDIV_12_OWNBUS", "qindiv12Ownbus");
    register.accept("QINDIV_12_RELBUS", "qindiv12Relbus");
    register.accept("QINDIV_12_LOOK4", "qindiv12Look4");
    register.accept("QINDIV_12_DIFJOB", "qindiv12Difjob");
    register.accept("QINDIV_12_INDOUT", "qindiv12Indout");
    register.accept("QINDIV_13_WRKING", "qindiv13Wrking");
    register.accept("QINDIV_13_JBAWAY", "qindiv13Jbaway");
    register.accept("QINDIV_13_OWNBUS", "qindiv13Ownbus");
    register.accept("QINDIV_13_RELBUS", "qindiv13Relbus");
    register.accept("QINDIV_13_LOOK4", "qindiv13Look4");
    register.accept("QINDIV_13_DIFJOB", "qindiv13Difjob");
    register.accept("QINDIV_13_INDOUT", "qindiv13Indout");
    register.accept("QINDIV_14_WRKING", "qindiv14Wrking");
    register.accept("QINDIV_14_JBAWAY", "qindiv14Jbaway");
    register.accept("QINDIV_14_OWNBUS", "qindiv14Ownbus");
    register.accept("QINDIV_14_RELBUS", "qindiv14Relbus");
    register.accept("QINDIV_14_LOOK4", "qindiv14Look4");
    register.accept("QINDIV_14_DIFJOB", "qindiv14Difjob");
    register.accept("QINDIV_14_INDOUT", "qindiv14Indout");
    register.accept("QINDIV_15_WRKING", "qindiv15Wrking");
    register.accept("QINDIV_15_JBAWAY", "qindiv15Jbaway");
    register.accept("QINDIV_15_OWNBUS", "qindiv15Ownbus");
    register.accept("QINDIV_15_RELBUS", "qindiv15Relbus");
    register.accept("QINDIV_15_LOOK4", "qindiv15Look4");
    register.accept("QINDIV_15_DIFJOB", "qindiv15Difjob");
    register.accept("QINDIV_15_INDOUT", "qindiv15Indout");
    register.accept("QINDIV_16_WRKING", "qindiv16Wrking");
    register.accept("QINDIV_16_JBAWAY", "qindiv16Jbaway");
    register.accept("QINDIV_16_OWNBUS", "qindiv16Ownbus");
    register.accept("QINDIV_16_RELBUS", "qindiv16Relbus");
    register.accept("QINDIV_16_LOOK4", "qindiv16Look4");
    register.accept("QINDIV_16_DIFJOB", "qindiv16Difjob");
    register.accept("QINDIV_16_INDOUT", "qindiv16Indout");
    register.accept("WEEK", "week");
    register.accept("W1YR", "w1yr");
    register.accept("QRTR", "qrtr");
    register.accept("WAVFND", "wavfnd");
    register.accept("HHLD", "hhld");
    register.accept("CHKLET", "chklet");
    register.accept("DIVADDIND", "divAddInd");
    register.accept("MO", "mo");
    register.accept("HOUT", "hout");
    // register.accept("LSTHO", "lstho");
    register.accept("LFSSAMP", "lfssamp");
    register.accept("THANKS", "thanks");
    register.accept("THANKE", "thanke");
    register.accept("RECPHONE", "recPhone");
    register.accept("COUNTRY", "country");
    register.accept("NUMPER", "numper");
    // previous waves
    register.accept("COMMENT_1_REFDTE", "comment1Refdte");
    register.accept("COMMENT_1_INTVNO", "comment1Intvno");
    register.accept("COMMENT_1_BriefSDC1", "comment1BriefSDC1");
    register.accept("COMMENT_1_BriefSDC2", "comment1BriefSDC2");
    register.accept("COMMENT_1_BriefSDC3", "comment1BriefSDC3");
    register.accept("COMMENT_1_BRIEF1", "comment1Brief1");
    register.accept("COMMENT_2_REFDTE", "comment2Refdte");
    register.accept("COMMENT_2_INTVNO", "comment2Intvno");
    register.accept("COMMENT_2_BriefSDC1", "comment2BriefSDC1");
    register.accept("COMMENT_2_BriefSDC2", "comment2BriefSDC2");
    register.accept("COMMENT_2_BriefSDC3", "comment2BriefSDC3");
    register.accept("COMMENT_2_BRIEF1", "comment2Brief1");
    register.accept("COMMENT_3_REFDTE", "comment3Refdte");
    register.accept("COMMENT_3_INTVNO", "comment3Intvno");
    register.accept("COMMENT_3_BriefSDC1", "comment3BriefSDC1");
    register.accept("COMMENT_3_BriefSDC2", "comment3BriefSDC2");
    register.accept("COMMENT_3_BriefSDC3", "comment3BriefSDC3");
    register.accept("COMMENT_3_BRIEF1", "comment3Brief1");
    register.accept("COMMENT_4_REFDTE", "comment4Refdte");
    register.accept("COMMENT_4_INTVNO", "comment4Intvno");
    register.accept("COMMENT_4_BriefSDC1", "comment4BriefSDC1");
    register.accept("COMMENT_4_BriefSDC2", "comment4BriefSDC2");
    register.accept("COMMENT_4_BriefSDC3", "comment4BriefSDC3");
    register.accept("COMMENT_4_BRIEF1", "comment4Brief1");
    register.accept("COMMENT_5_REFDTE", "comment5Refdte");
    register.accept("COMMENT_5_INTVNO", "comment5Intvno");
    register.accept("COMMENT_5_BriefSDC1", "comment5BriefSDC1");
    register.accept("COMMENT_5_BriefSDC2", "comment5BriefSDC2");
    register.accept("COMMENT_5_BriefSDC3", "comment5BriefSDC3");
    register.accept("COMMENT_5_BRIEF1", "comment5Brief1");
    register.accept("TELENO", "teleno");
    register.accept("DIRECTION", "direction");
    register.accept("Issue_No", "issueNo");
    register.accept("Part", "part");
    register.accept("Auth", "auth");
    register.accept("EmployeeNo", "employeeno");
    register.accept("Last_Updated", "lastUpdated");
    CSV_HEADERS = csvHeaders;
    DATA_FIELDS = dataFields;
    SAMPLE_LFS_DATA_COLUMN_MAP = map;
  }

  private CsvToBean<LegacyLFSSampleEntityRaw> csvToBean;

  public LegacyLFSSampleReader(InputStream stream) {
    HeaderColumnNameTranslateMappingStrategy<LegacyLFSSampleEntityRaw> strategy =
        new HeaderColumnNameTranslateMappingStrategy<>();
    strategy.setType(LegacyLFSSampleEntityRaw.class);
    strategy.setColumnMapping(SAMPLE_LFS_DATA_COLUMN_MAP);
    CsvToBeanBuilder<LegacyLFSSampleEntityRaw> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
    csvToBean = builder
        .withMappingStrategy(strategy)
        .build();
  }

  public Iterator<LegacySampleEntity> iterator() {
    return new LegacyLFSSampleIterator(csvToBean.iterator());
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static public class LegacyLFSSampleEntityRaw {
    String serno;
    String tla;
    String fp;
    String quotaNo;
    String refDate;
    String lstho;
    String thiswv;
    String prem1;
    String prem2;
    String prem3;
    String prem4;
    String district;
    String postTown;
    String postcode;
    String quota;
    String addr;
    String osGridRef;
    String year;
    String month;
    String main;
    String numhhld;
    String hhlddesc;
    // respondents
    String qresLineName1;
    String qresLineName2;
    String qresLineName3;
    String qresLineName4;
    String qresLineName5;
    String qresLineName6;
    String qresLineName7;
    String qresLineName8;
    String qresLineName9;
    String qresLineName10;
    String qresLineName11;
    String qresLineName12;
    String qresLineName13;
    String qresLineName14;
    String qresLineName15;
    String qresLineName16;
    String qresLineAge1;
    String qresLineAge2;
    String qresLineAge3;
    String qresLineAge4;
    String qresLineAge5;
    String qresLineAge6;
    String qresLineAge7;
    String qresLineAge8;
    String qresLineAge9;
    String qresLineAge10;
    String qresLineAge11;
    String qresLineAge12;
    String qresLineAge13;
    String qresLineAge14;
    String qresLineAge15;
    String qresLineAge16;
    String qindiv1Wrking;
    String qindiv1Jbaway;
    String qindiv1Ownbus;
    String qindiv1Relbus;
    String qindiv1Look4;
    String qindiv1Difjob;
    String qindiv1Indout;
    String qindiv2Wrking;
    String qindiv2Jbaway;
    String qindiv2Ownbus;
    String qindiv2Relbus;
    String qindiv2Look4;
    String qindiv2Difjob;
    String qindiv2Indout;
    String qindiv3Wrking;
    String qindiv3Jbaway;
    String qindiv3Ownbus;
    String qindiv3Relbus;
    String qindiv3Look4;
    String qindiv3Difjob;
    String qindiv3Indout;
    String qindiv4Wrking;
    String qindiv4Jbaway;
    String qindiv4Ownbus;
    String qindiv4Relbus;
    String qindiv4Look4;
    String qindiv4Difjob;
    String qindiv4Indout;
    String qindiv5Wrking;
    String qindiv5Jbaway;
    String qindiv5Ownbus;
    String qindiv5Relbus;
    String qindiv5Look4;
    String qindiv5Difjob;
    String qindiv5Indout;
    String qindiv6Wrking;
    String qindiv6Jbaway;
    String qindiv6Ownbus;
    String qindiv6Relbus;
    String qindiv6Look4;
    String qindiv6Difjob;
    String qindiv6Indout;
    String qindiv7Wrking;
    String qindiv7Jbaway;
    String qindiv7Ownbus;
    String qindiv7Relbus;
    String qindiv7Look4;
    String qindiv7Difjob;
    String qindiv7Indout;
    String qindiv8Wrking;
    String qindiv8Jbaway;
    String qindiv8Ownbus;
    String qindiv8Relbus;
    String qindiv8Look4;
    String qindiv8Difjob;
    String qindiv8Indout;
    String qindiv9Wrking;
    String qindiv9Jbaway;
    String qindiv9Ownbus;
    String qindiv9Relbus;
    String qindiv9Look4;
    String qindiv9Difjob;
    String qindiv9Indout;
    String qindiv10Wrking;
    String qindiv10Jbaway;
    String qindiv10Ownbus;
    String qindiv10Relbus;
    String qindiv10Look4;
    String qindiv10Difjob;
    String qindiv10Indout;
    String qindiv11Wrking;
    String qindiv11Jbaway;
    String qindiv11Ownbus;
    String qindiv11Relbus;
    String qindiv11Look4;
    String qindiv11Difjob;
    String qindiv11Indout;
    String qindiv12Wrking;
    String qindiv12Jbaway;
    String qindiv12Ownbus;
    String qindiv12Relbus;
    String qindiv12Look4;
    String qindiv12Difjob;
    String qindiv12Indout;
    String qindiv13Wrking;
    String qindiv13Jbaway;
    String qindiv13Ownbus;
    String qindiv13Relbus;
    String qindiv13Look4;
    String qindiv13Difjob;
    String qindiv13Indout;
    String qindiv14Wrking;
    String qindiv14Jbaway;
    String qindiv14Ownbus;
    String qindiv14Relbus;
    String qindiv14Look4;
    String qindiv14Difjob;
    String qindiv14Indout;
    String qindiv15Wrking;
    String qindiv15Jbaway;
    String qindiv15Ownbus;
    String qindiv15Relbus;
    String qindiv15Look4;
    String qindiv15Difjob;
    String qindiv15Indout;
    String qindiv16Wrking;
    String qindiv16Jbaway;
    String qindiv16Ownbus;
    String qindiv16Relbus;
    String qindiv16Look4;
    String qindiv16Difjob;
    String qindiv16Indout;
    String week;
    String w1yr;
    String qrtr;
    String wavfnd;
    String hhld;
    String chklet;
    String divAddInd;
    String mo;
    String hout;
    // String lstho;
    String lfssamp;
    String thanks;
    String thanke;
    String recPhone;
    String country;
    String numper;
    // previous waves
    String comment1Refdte;
    String comment1Intvno;
    String comment1BriefSDC1;
    String comment1BriefSDC2;
    String comment1BriefSDC3;
    String comment1Brief1;
    String comment2Refdte;
    String comment2Intvno;
    String comment2BriefSDC1;
    String comment2BriefSDC2;
    String comment2BriefSDC3;
    String comment2Brief1;
    String comment3Refdte;
    String comment3Intvno;
    String comment3BriefSDC1;
    String comment3BriefSDC2;
    String comment3BriefSDC3;
    String comment3Brief1;
    String comment4Refdte;
    String comment4Intvno;
    String comment4BriefSDC1;
    String comment4BriefSDC2;
    String comment4BriefSDC3;
    String comment4Brief1;
    String comment5Refdte;
    String comment5Intvno;
    String comment5BriefSDC1;
    String comment5BriefSDC2;
    String comment5BriefSDC3;
    String comment5Brief1;
    String teleno;
    String direction;
    String issueNo;
    String part;
    String auth;
    String employeeNo;
    String lastUpdated;
  }

  class LegacyLFSSampleIterator implements Iterator<LegacySampleEntity> {
    Iterator<LegacyLFSSampleEntityRaw> rawIterator;

    LegacyLFSSampleIterator(Iterator<LegacyLFSSampleEntityRaw> rawIterator) {
      this.rawIterator = rawIterator;
    }

    @Override public boolean hasNext() {
      return rawIterator.hasNext();
    }

    @Override public LegacySampleEntity next() {
      LegacyLFSSampleEntityRaw raw = rawIterator.next();
      LegacySampleEntity entity = new LegacySampleEntity();
      entity.setSerno(raw.getSerno());
      entity.setTla(raw.getTla());
      entity.setStage(raw.getFp());
      entity.setQuota(raw.getQuota());
      entity.setAuthno(raw.getAuth());
      entity.setEmployeeno(raw.getEmployeeNo());
      entity.setAddressline1(raw.getPrem1());
      entity.setAddressline2(raw.getPrem2());
      entity.setAddressline3(raw.getPrem3());
      entity.setAddressline4(raw.getPrem4());
      entity.setDistrict(raw.getDistrict());
      entity.setPosttown(raw.getPostTown());
      entity.setPostcode(raw.getPostcode());
      entity.setAddressno(raw.getAddr());
      entity.setOsgridref(raw.getOsGridRef());
      entity.setKishgrid(null);
      return entity;
    }
  }

  private class LegacyLFSSampleCSVFilter implements CsvToBeanFilter {
    private final MappingStrategy strategy;

    LegacyLFSSampleCSVFilter(MappingStrategy strategy) {
      this.strategy = strategy;
    }

    @Override public boolean allowLine(String[] strings) {
      int sernoIndex = strategy.getColumnIndex("Serno");
      // TODO the rest of these
      // TODO verify what's in here for nulls
      // TODO add logging
      return false;
    }
  }
}

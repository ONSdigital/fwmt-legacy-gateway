package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;

@Slf4j
public class LegacyLFSSampleReader implements SampleReader {
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
    register.accept("POSTTOWN", "postTown");
    register.accept("POSTCODE", "postcode");
    register.accept("QUOTA", "quota");
    register.accept("ADDR", "addr");
    register.accept("OSGRIDREF", "osGridRef");
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
    register.accept("EmployeeNo", "employeeNo");
    register.accept("Last_Updated", "lastUpdated");
    CSV_HEADERS = csvHeaders;
    DATA_FIELDS = dataFields;
    SAMPLE_LFS_DATA_COLUMN_MAP = map;
  }

  private CsvToBean<LegacyLFSSampleEntityRaw> csvToBean;
  @Getter private List<UnprocessedCSVRow> unprocessedCSVRows;
  @Getter private int unprocessedCount;
  @Getter private int successCount;

  public LegacyLFSSampleReader(InputStream stream) {
    HeaderColumnNameTranslateMappingStrategy<LegacyLFSSampleEntityRaw> strategy =
        new HeaderColumnNameTranslateMappingStrategy<>();
    strategy.setType(LegacyLFSSampleEntityRaw.class);
    strategy.setColumnMapping(SAMPLE_LFS_DATA_COLUMN_MAP);
    CsvToBeanBuilder<LegacyLFSSampleEntityRaw> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
    this.unprocessedCSVRows = new ArrayList<>();
    this.csvToBean = builder
        .withMappingStrategy(strategy)
        .withFilter(new LegacyLFSSampleCSVFilter(strategy))
        .build();
  }

  public LegacyLFSSampleIterator iterator() {
    return new LegacyLFSSampleIterator(csvToBean.iterator());
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static public class LegacyLFSSampleEntityRaw {
    private String serno;
    private String tla;
    private String fp;
    private String quotaNo;
    private String refDate;
    private String lstho;
    private String thiswv;
    private String prem1;
    private String prem2;
    private String prem3;
    private String prem4;
    private String district;
    private String postTown;
    private String postcode;
    private String quota;
    private String addr;
    private String osGridRef;
    private String year;
    private String month;
    private String main;
    private String numhhld;
    private String hhlddesc;
    // respondents
    private String qresLineName1;
    private String qresLineName2;
    private String qresLineName3;
    private String qresLineName4;
    private String qresLineName5;
    private String qresLineName6;
    private String qresLineName7;
    private String qresLineName8;
    private String qresLineName9;
    private String qresLineName10;
    private String qresLineName11;
    private String qresLineName12;
    private String qresLineName13;
    private String qresLineName14;
    private String qresLineName15;
    private String qresLineName16;
    private String qresLineAge1;
    private String qresLineAge2;
    private String qresLineAge3;
    private String qresLineAge4;
    private String qresLineAge5;
    private String qresLineAge6;
    private String qresLineAge7;
    private String qresLineAge8;
    private String qresLineAge9;
    private String qresLineAge10;
    private String qresLineAge11;
    private String qresLineAge12;
    private String qresLineAge13;
    private String qresLineAge14;
    private String qresLineAge15;
    private String qresLineAge16;
    private String qindiv1Wrking;
    private String qindiv1Jbaway;
    private String qindiv1Ownbus;
    private String qindiv1Relbus;
    private String qindiv1Look4;
    private String qindiv1Difjob;
    private String qindiv1Indout;
    private String qindiv2Wrking;
    private String qindiv2Jbaway;
    private String qindiv2Ownbus;
    private String qindiv2Relbus;
    private String qindiv2Look4;
    private String qindiv2Difjob;
    private String qindiv2Indout;
    private String qindiv3Wrking;
    private String qindiv3Jbaway;
    private String qindiv3Ownbus;
    private String qindiv3Relbus;
    private String qindiv3Look4;
    private String qindiv3Difjob;
    private String qindiv3Indout;
    private String qindiv4Wrking;
    private String qindiv4Jbaway;
    private String qindiv4Ownbus;
    private String qindiv4Relbus;
    private String qindiv4Look4;
    private String qindiv4Difjob;
    private String qindiv4Indout;
    private String qindiv5Wrking;
    private String qindiv5Jbaway;
    private String qindiv5Ownbus;
    private String qindiv5Relbus;
    private String qindiv5Look4;
    private String qindiv5Difjob;
    private String qindiv5Indout;
    private String qindiv6Wrking;
    private String qindiv6Jbaway;
    private String qindiv6Ownbus;
    private String qindiv6Relbus;
    private String qindiv6Look4;
    private String qindiv6Difjob;
    private String qindiv6Indout;
    private String qindiv7Wrking;
    private String qindiv7Jbaway;
    private String qindiv7Ownbus;
    private String qindiv7Relbus;
    private String qindiv7Look4;
    private String qindiv7Difjob;
    private String qindiv7Indout;
    private String qindiv8Wrking;
    private String qindiv8Jbaway;
    private String qindiv8Ownbus;
    private String qindiv8Relbus;
    private String qindiv8Look4;
    private String qindiv8Difjob;
    private String qindiv8Indout;
    private String qindiv9Wrking;
    private String qindiv9Jbaway;
    private String qindiv9Ownbus;
    private String qindiv9Relbus;
    private String qindiv9Look4;
    private String qindiv9Difjob;
    private String qindiv9Indout;
    private String qindiv10Wrking;
    private String qindiv10Jbaway;
    private String qindiv10Ownbus;
    private String qindiv10Relbus;
    private String qindiv10Look4;
    private String qindiv10Difjob;
    private String qindiv10Indout;
    private String qindiv11Wrking;
    private String qindiv11Jbaway;
    private String qindiv11Ownbus;
    private String qindiv11Relbus;
    private String qindiv11Look4;
    private String qindiv11Difjob;
    private String qindiv11Indout;
    private String qindiv12Wrking;
    private String qindiv12Jbaway;
    private String qindiv12Ownbus;
    private String qindiv12Relbus;
    private String qindiv12Look4;
    private String qindiv12Difjob;
    private String qindiv12Indout;
    private String qindiv13Wrking;
    private String qindiv13Jbaway;
    private String qindiv13Ownbus;
    private String qindiv13Relbus;
    private String qindiv13Look4;
    private String qindiv13Difjob;
    private String qindiv13Indout;
    private String qindiv14Wrking;
    private String qindiv14Jbaway;
    private String qindiv14Ownbus;
    private String qindiv14Relbus;
    private String qindiv14Look4;
    private String qindiv14Difjob;
    private String qindiv14Indout;
    private String qindiv15Wrking;
    private String qindiv15Jbaway;
    private String qindiv15Ownbus;
    private String qindiv15Relbus;
    private String qindiv15Look4;
    private String qindiv15Difjob;
    private String qindiv15Indout;
    private String qindiv16Wrking;
    private String qindiv16Jbaway;
    private String qindiv16Ownbus;
    private String qindiv16Relbus;
    private String qindiv16Look4;
    private String qindiv16Difjob;
    private String qindiv16Indout;
    private String week;
    private String w1yr;
    private String qrtr;
    private String wavfnd;
    private String hhld;
    private String chklet;
    private String divAddInd;
    private String mo;
    private String hout;
    // String lstho;
    private String lfssamp;
    private String thanks;
    private String thanke;
    private String recPhone;
    private String country;
    private String numper;
    // previous waves
    private String comment1Refdte;
    private String comment1Intvno;
    private String comment1BriefSDC1;
    private String comment1BriefSDC2;
    private String comment1BriefSDC3;
    private String comment1Brief1;
    private String comment2Refdte;
    private String comment2Intvno;
    private String comment2BriefSDC1;
    private String comment2BriefSDC2;
    private String comment2BriefSDC3;
    private String comment2Brief1;
    private String comment3Refdte;
    private String comment3Intvno;
    private String comment3BriefSDC1;
    private String comment3BriefSDC2;
    private String comment3BriefSDC3;
    private String comment3Brief1;
    private String comment4Refdte;
    private String comment4Intvno;
    private String comment4BriefSDC1;
    private String comment4BriefSDC2;
    private String comment4BriefSDC3;
    private String comment4Brief1;
    private String comment5Refdte;
    private String comment5Intvno;
    private String comment5BriefSDC1;
    private String comment5BriefSDC2;
    private String comment5BriefSDC3;
    private String comment5Brief1;
    private String teleno;
    private String direction;
    private String issueNo;
    private String part;
    private String auth;
    private String employeeNo;
    private String lastUpdated;
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
      entity.setLegacyJobId(raw.getSerno() + raw.getFp() + raw.getTla());
      entity.setSerno(raw.getSerno());
      entity.setTla(raw.getTla());
      entity.setFp(raw.getFp());
      entity.setQuota(raw.getQuota());
      entity.setAuthNo(raw.getAuth());
      entity.setEmployeeNo(raw.getEmployeeNo());
      entity.setAddressLine1(raw.getPrem1());
      entity.setAddressLine2(raw.getPrem2());
      entity.setAddressLine3(raw.getPrem3());
      entity.setAddressLine4(raw.getPrem4());
      entity.setDistrict(raw.getDistrict());
      entity.setPostTown(raw.getPostTown());
      entity.setPostcode(raw.getPostcode());
      entity.setAddressNo(raw.getAddr());
      entity.setOsGridRef(raw.getOsGridRef());
      entity.setWeek(raw.getWeek());
      entity.setW1yr(raw.getW1yr());
      entity.setQrtr(raw.getQrtr());
      entity.setWavfnd(raw.getWavfnd());
      entity.setHhld(raw.getHhld());
      entity.setChklet(raw.getChklet());
      return entity;
    }

    public LegacyLFSSampleEntityRaw nextRaw() {
      return rawIterator.next();
    }
  }

  private class LegacyLFSSampleCSVFilter implements CsvToBeanFilter {
    private final String[] requiredFields = {"SERNO", "TLA", "FP", "Quota_No", "Auth", "EmployeeNo", "PREM1",
        "PREM2", "PREM3", "PREM4", "DISTRICT", "POSTTOWN", "POSTCODE", "ADDR", "OSGRIDREF"};

    private final MappingStrategy strategy;

    // TODO ensure that this lineCounter always begins at 1 - We must be sure that this instance is never re-used
    // It begins at 1 as the first line of the CSV is skipped
    // It should be incremented every time we begin a new line
    private int lineCounter = 1;

    LegacyLFSSampleCSVFilter(MappingStrategy strategy) {
      this.strategy = strategy;
    }

    private void fail(String[] strings, String reason) {
      unprocessedCount++;
      unprocessedCSVRows.add(new UnprocessedCSVRow(strings, lineCounter, reason));
    }

    @Override public boolean allowLine(String[] strings) {
      lineCounter++;
      for (String field : requiredFields) {
        Integer index = strategy.getColumnIndex(field);
        if (index == null) {
          // TODO add error
          fail(strings, field + " could not be found, but is required");
          return false;
        }
        String value = strings[index];
        if (value == null) {
          fail(strings, field + " was null, but is required");
          return false;
        }
        if (value.length() == 0) {
          fail(strings, field + " was empty, but is required");
          return false;
        }
      }
      successCount++;
      return true;
    }
  }
}

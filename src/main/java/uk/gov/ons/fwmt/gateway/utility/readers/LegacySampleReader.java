package uk.gov.ons.fwmt.gateway.utility.readers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

@Slf4j
public class LegacySampleReader extends CsvToBean<LegacySampleEntity> {
    private static final String SERNO = "serno";
    private static final String TLA = "tla";
    private static final String FP = "fp";
    private static final String Quota_No = "quotaNo";
    private static final String Issue_No = "issueNo";
    private static final String Part = "part";
    private static final String Auth = "auth";
    private static final String EmployeeNo = "employeeNo";
    private static final String Last_Updated = "lastUpdated";
    private static final String RefDate = "refDate";
    private static final String QUOTA = "quota";
    private static final String WEEK = "week";
    private static final String W1YR = "w1yr";
    private static final String QRTR = "qrtr";
    private static final String ADDR = "addr";
    private static final String WAVFND = "wavfnd";
    private static final String HHLD = "hhld";
    private static final String CHKLET = "chklet";
    private static final String THISWV = "thiswv";
    private static final String PREM1 = "prem1";
    private static final String PREM2 = "prem2";
    private static final String PREM3 = "prem3";
    private static final String PREM4 = "prem4";
    private static final String DISTRICT = "district";
    private static final String POSTTOWN = "posttown";
    private static final String POSTCODE = "postcode";
    private static final String DIVADDIND = "divaddind";
    private static final String MO = "mo";
    private static final String DIRECTION = "direction";
    private static final String HOUT = "hout";
    private static final String LSTHO = "lstho";
    private static final String LFSSAMP = "lfssamp";
    private static final String THANKS = "thanks";
    private static final String THANKE = "thanke";
    private static final String TELENO = "teleNo";
    private static final String RECPHONE = "reception";
    private static final String COUNTRY = "country";
    private static final String OSGRIDREF = "osGridRef";
    private static final String MAIN = "main";
    private static final String NUMHHLD = "numHhld";
    private static final String HHLDDESC = "hhldDesc";
    private static final String NUMPER = "numper";
    private static final String QRES_LINE_NAME_1 = "qresLineName1";
    private static final String QRES_LINE_NAME_2 = "qresLineName2";
    private static final String QRES_LINE_NAME_3 = "qresLineName3";
    private static final String QRES_LINE_NAME_4 = "qresLineName4";
    private static final String QRES_LINE_NAME_5 = "qresLineName5";
    private static final String QRES_LINE_NAME_6 = "qresLineName6";
    private static final String QRES_LINE_NAME_7 = "qresLineName7";
    private static final String QRES_LINE_NAME_8 = "qresLineName8";
    private static final String QRES_LINE_NAME_9 = "qresLineName9";
    private static final String QRES_LINE_NAME_10 = "qresLineName10";
    private static final String QRES_LINE_NAME_11 = "qresLineName11";
    private static final String QRES_LINE_NAME_12 = "qresLineName12";
    private static final String QRES_LINE_NAME_13 = "qresLineName13";
    private static final String QRES_LINE_NAME_14 = "qresLineName14";
    private static final String QRES_LINE_NAME_15 = "qresLineName15";
    private static final String QRES_LINE_NAME_16 = "qresLineName16";
    private static final String QRES_LINE_AGE_1 = "qresLineAge1";
    private static final String QRES_LINE_AGE_2 = "qresLineAge2";
    private static final String QRES_LINE_AGE_3 = "qresLineAge3";
    private static final String QRES_LINE_AGE_4 = "qresLineAge4";
    private static final String QRES_LINE_AGE_5 = "qresLineAge5";
    private static final String QRES_LINE_AGE_6 = "qresLineAge6";
    private static final String QRES_LINE_AGE_7 = "qresLineAge7";
    private static final String QRES_LINE_AGE_8 = "qresLineAge8";
    private static final String QRES_LINE_AGE_9 = "qresLineAge9";
    private static final String QRES_LINE_AGE_10 = "qresLineAge10";
    private static final String QRES_LINE_AGE_11 = "qresLineAge11";
    private static final String QRES_LINE_AGE_12 = "qresLineAge12";
    private static final String QRES_LINE_AGE_13 = "qresLineAge13";
    private static final String QRES_LINE_AGE_14 = "qresLineAge14";
    private static final String QRES_LINE_AGE_15 = "qresLineAge15";
    private static final String QRES_LINE_AGE_16 = "qresLineAge16";
    private static final String QINDIV_1_WRKING = "qindiv1Wrking";
    private static final String QINDIV_1_JBAWAY = "qindiv1Jbaway";
    private static final String QINDIV_1_OWNBUS = "qindiv1Ownbus";
    private static final String QINDIV_1_RELBUS = "qindiv1Relbus";
    private static final String QINDIV_1_LOOK4 = "qindiv1Look4";
    private static final String QINDIV_1_DIFJOB = "qindiv1Difjob";
    private static final String QINDIV_1_INDOUT = "qindiv1Indout";
    private static final String QINDIV_2_WRKING = "qindiv2Wrking";
    private static final String QINDIV_2_JBAWAY = "qindiv2Jbaway";
    private static final String QINDIV_2_OWNBUS = "qindiv2Ownbus";
    private static final String QINDIV_2_RELBUS = "qindiv2Relbus";
    private static final String QINDIV_2_LOOK4 = "qindiv2Look4";
    private static final String QINDIV_2_DIFJOB = "qindiv2Difjob";
    private static final String QINDIV_2_INDOUT = "qindiv2Indout";
    private static final String QINDIV_3_WRKING = "qindiv3Wrking";
    private static final String QINDIV_3_JBAWAY = "qindiv3Jbaway";
    private static final String QINDIV_3_OWNBUS = "qindiv3Ownbus";
    private static final String QINDIV_3_RELBUS = "qindiv3Relbus";
    private static final String QINDIV_3_LOOK4 = "qindiv3Look4";
    private static final String QINDIV_3_DIFJOB = "qindiv3Difjob";
    private static final String QINDIV_3_INDOUT = "qindiv3Indout";
    private static final String QINDIV_4_WRKING = "qindiv4Wrking";
    private static final String QINDIV_4_JBAWAY = "qindiv4Jbaway";
    private static final String QINDIV_4_OWNBUS = "qindiv4Ownbus";
    private static final String QINDIV_4_RELBUS = "qindiv4Relbus";
    private static final String QINDIV_4_LOOK4 = "qindiv4Look4";
    private static final String QINDIV_4_DIFJOB = "qindiv4Difjob";
    private static final String QINDIV_4_INDOUT = "qindiv4Indout";
    private static final String QINDIV_5_WRKING = "qindiv5Wrking";
    private static final String QINDIV_5_JBAWAY = "qindiv5Jbaway";
    private static final String QINDIV_5_OWNBUS = "qindiv5Ownbus";
    private static final String QINDIV_5_RELBUS = "qindiv5Relbus";
    private static final String QINDIV_5_LOOK4 = "qindiv5Look4";
    private static final String QINDIV_5_DIFJOB = "qindiv5Difjob";
    private static final String QINDIV_5_INDOUT = "qindiv5Indout";
    private static final String QINDIV_6_WRKING = "qindiv6Wrking";
    private static final String QINDIV_6_JBAWAY = "qindiv6Jbaway";
    private static final String QINDIV_6_OWNBUS = "qindiv6Ownbus";
    private static final String QINDIV_6_RELBUS = "qindiv6Relbus";
    private static final String QINDIV_6_LOOK4 = "qindiv6Look4";
    private static final String QINDIV_6_DIFJOB = "qindiv6Difjob";
    private static final String QINDIV_6_INDOUT = "qindiv6Indout";
    private static final String QINDIV_7_WRKING = "qindiv7Wrking";
    private static final String QINDIV_7_JBAWAY = "qindiv7Jbaway";
    private static final String QINDIV_7_OWNBUS = "qindiv7Ownbus";
    private static final String QINDIV_7_RELBUS = "qindiv7Relbus";
    private static final String QINDIV_7_LOOK4 = "qindiv7Look4";
    private static final String QINDIV_7_DIFJOB = "qindiv7Difjob";
    private static final String QINDIV_7_INDOUT = "qindiv7Indout";
    private static final String QINDIV_8_WRKING = "qindiv8Wrking";
    private static final String QINDIV_8_JBAWAY = "qindiv8Jbaway";
    private static final String QINDIV_8_OWNBUS = "qindiv8Ownbus";
    private static final String QINDIV_8_RELBUS = "qindiv8Relbus";
    private static final String QINDIV_8_LOOK4 = "qindiv8Look4";
    private static final String QINDIV_8_DIFJOB = "qindiv8Difjob";
    private static final String QINDIV_8_INDOUT = "qindiv8Indout";
    private static final String QINDIV_9_WRKING = "qindiv9Wrking";
    private static final String QINDIV_9_JBAWAY = "qindiv9Jbaway";
    private static final String QINDIV_9_OWNBUS = "qindiv9Ownbus";
    private static final String QINDIV_9_RELBUS = "qindiv9Relbus";
    private static final String QINDIV_9_LOOK4 = "qindiv9Look4";
    private static final String QINDIV_9_DIFJOB = "qindiv9Difjob";
    private static final String QINDIV_9_INDOUT = "qindiv9Indout";
    private static final String QINDIV_10_WRKING = "qindiv10Wrking";
    private static final String QINDIV_10_JBAWAY = "qindiv10Jbaway";
    private static final String QINDIV_10_OWNBUS = "qindiv10Ownbus";
    private static final String QINDIV_10_RELBUS = "qindiv10Relbus";
    private static final String QINDIV_10_LOOK4 = "qindiv10Look4";
    private static final String QINDIV_10_DIFJOB = "qindiv10Difjob";
    private static final String QINDIV_10_INDOUT = "qindiv10Indout";
    private static final String QINDIV_11_WRKING = "qindiv11Wrking";
    private static final String QINDIV_11_JBAWAY = "qindiv11Jbaway";
    private static final String QINDIV_11_OWNBUS = "qindiv11Ownbus";
    private static final String QINDIV_11_RELBUS = "qindiv11Relbus";
    private static final String QINDIV_11_LOOK4 = "qindiv11Look4";
    private static final String QINDIV_11_DIFJOB = "qindiv11Difjob";
    private static final String QINDIV_11_INDOUT = "qindiv11Indout";
    private static final String QINDIV_12_WRKING = "qindiv12Wrking";
    private static final String QINDIV_12_JBAWAY = "qindiv12Jbaway";
    private static final String QINDIV_12_OWNBUS = "qindiv12Ownbus";
    private static final String QINDIV_12_RELBUS = "qindiv12Relbus";
    private static final String QINDIV_12_LOOK4 = "qindiv12Look4";
    private static final String QINDIV_12_DIFJOB = "qindiv12Difjob";
    private static final String QINDIV_12_INDOUT = "qindiv12Indout";
    private static final String QINDIV_13_WRKING = "qindiv13Wrking";
    private static final String QINDIV_13_JBAWAY = "qindiv13Jbaway";
    private static final String QINDIV_13_OWNBUS = "qindiv13Ownbus";
    private static final String QINDIV_13_RELBUS = "qindiv13Relbus";
    private static final String QINDIV_13_LOOK4 = "qindiv13Look4";
    private static final String QINDIV_13_DIFJOB = "qindiv13Difjob";
    private static final String QINDIV_13_INDOUT = "qindiv13Indout";
    private static final String QINDIV_14_WRKING = "qindiv14Wrking";
    private static final String QINDIV_14_JBAWAY = "qindiv14Jbaway";
    private static final String QINDIV_14_OWNBUS = "qindiv14Ownbus";
    private static final String QINDIV_14_RELBUS = "qindiv14Relbus";
    private static final String QINDIV_14_LOOK4 = "qindiv14Look4";
    private static final String QINDIV_14_DIFJOB = "qindiv14Difjob";
    private static final String QINDIV_14_INDOUT = "qindiv14Indout";
    private static final String QINDIV_15_WRKING = "qindiv15Wrking";
    private static final String QINDIV_15_JBAWAY = "qindiv15Jbaway";
    private static final String QINDIV_15_OWNBUS = "qindiv15Ownbus";
    private static final String QINDIV_15_RELBUS = "qindiv15Relbus";
    private static final String QINDIV_15_LOOK4 = "qindiv15Look4";
    private static final String QINDIV_15_DIFJOB = "qindiv15Difjob";
    private static final String QINDIV_15_INDOUT = "qindiv15Indout";
    private static final String QINDIV_16_WRKING = "qindiv16Wrking";
    private static final String QINDIV_16_JBAWAY = "qindiv16Jbaway";
    private static final String QINDIV_16_OWNBUS = "qindiv16Ownbus";
    private static final String QINDIV_16_RELBUS = "qindiv16Relbus";
    private static final String QINDIV_16_LOOK4 = "qindiv16Look4";
    private static final String QINDIV_16_DIFJOB = "qindiv16Difjob";
    private static final String QINDIV_16_INDOUT = "qindiv16Indout";
    private static final String INTVNO = "intvno";
    private static final String BriefSDC1 = "briefSDC1";
    private static final String BriefSDC2 = "briefSDC2";
    private static final String BriefSDC3 = "briefSDC3";
    private static final String BRIEF1 = "brief1";

    private static final String[] SAMPLE_DATA_COLUMNS = new String[] { SERNO, TLA, FP, Quota_No, Issue_No, Part, Auth,
            EmployeeNo, Last_Updated, RefDate, QUOTA, WEEK, W1YR, QRTR, ADDR, WAVFND, HHLD, CHKLET, THISWV, PREM1,
            PREM2, PREM3, PREM4, DISTRICT, POSTTOWN, POSTCODE, DIVADDIND, MO, DIRECTION, HOUT, LSTHO, LFSSAMP, THANKS,
            THANKE, TELENO, RECPHONE, COUNTRY, OSGRIDREF, MAIN, NUMHHLD, HHLDDESC, NUMPER, QRES_LINE_NAME_1,
            QRES_LINE_NAME_2, QRES_LINE_NAME_3, QRES_LINE_NAME_4, QRES_LINE_NAME_5, QRES_LINE_NAME_6, QRES_LINE_NAME_7,
            QRES_LINE_NAME_8, QRES_LINE_NAME_9, QRES_LINE_NAME_10, QRES_LINE_NAME_11, QRES_LINE_NAME_12,
            QRES_LINE_NAME_13, QRES_LINE_NAME_14, QRES_LINE_NAME_15, QRES_LINE_NAME_16, QRES_LINE_AGE_1,
            QRES_LINE_AGE_2, QRES_LINE_AGE_3, QRES_LINE_AGE_4, QRES_LINE_AGE_5, QRES_LINE_AGE_6, QRES_LINE_AGE_7,
            QRES_LINE_AGE_8, QRES_LINE_AGE_9, QRES_LINE_AGE_10, QRES_LINE_AGE_11, QRES_LINE_AGE_12, QRES_LINE_AGE_13,
            QRES_LINE_AGE_14, QRES_LINE_AGE_15, QRES_LINE_AGE_16, QINDIV_1_WRKING, QINDIV_1_JBAWAY, QINDIV_1_OWNBUS,
            QINDIV_1_RELBUS, QINDIV_1_LOOK4, QINDIV_1_DIFJOB, QINDIV_1_INDOUT, QINDIV_2_WRKING, QINDIV_2_JBAWAY,
            QINDIV_2_OWNBUS, QINDIV_2_RELBUS, QINDIV_2_LOOK4, QINDIV_2_DIFJOB, QINDIV_2_INDOUT, QINDIV_3_WRKING,
            QINDIV_3_JBAWAY, QINDIV_3_OWNBUS, QINDIV_3_RELBUS, QINDIV_3_LOOK4, QINDIV_3_DIFJOB, QINDIV_3_INDOUT,
            QINDIV_4_WRKING, QINDIV_4_JBAWAY, QINDIV_4_OWNBUS, QINDIV_4_RELBUS, QINDIV_4_LOOK4, QINDIV_4_DIFJOB,
            QINDIV_4_INDOUT, QINDIV_5_WRKING, QINDIV_5_JBAWAY, QINDIV_5_OWNBUS, QINDIV_5_RELBUS, QINDIV_5_LOOK4,
            QINDIV_5_DIFJOB, QINDIV_5_INDOUT, QINDIV_6_WRKING, QINDIV_6_JBAWAY, QINDIV_6_OWNBUS, QINDIV_6_RELBUS,
            QINDIV_6_LOOK4, QINDIV_6_DIFJOB, QINDIV_6_INDOUT, QINDIV_7_WRKING, QINDIV_7_JBAWAY, QINDIV_7_OWNBUS,
            QINDIV_7_RELBUS, QINDIV_7_LOOK4, QINDIV_7_DIFJOB, QINDIV_7_INDOUT, QINDIV_8_WRKING, QINDIV_8_JBAWAY,
            QINDIV_8_OWNBUS, QINDIV_8_RELBUS, QINDIV_8_LOOK4, QINDIV_8_DIFJOB, QINDIV_8_INDOUT, QINDIV_9_WRKING,
            QINDIV_9_JBAWAY, QINDIV_9_OWNBUS, QINDIV_9_RELBUS, QINDIV_9_LOOK4, QINDIV_9_DIFJOB, QINDIV_9_INDOUT,
            QINDIV_10_WRKING, QINDIV_10_JBAWAY, QINDIV_10_OWNBUS, QINDIV_10_RELBUS, QINDIV_10_LOOK4, QINDIV_10_DIFJOB,
            QINDIV_10_INDOUT, QINDIV_11_WRKING, QINDIV_11_JBAWAY, QINDIV_11_OWNBUS, QINDIV_11_RELBUS, QINDIV_11_LOOK4,
            QINDIV_11_DIFJOB, QINDIV_11_INDOUT, QINDIV_12_WRKING, QINDIV_12_JBAWAY, QINDIV_12_OWNBUS, QINDIV_12_RELBUS,
            QINDIV_12_LOOK4, QINDIV_12_DIFJOB, QINDIV_12_INDOUT, QINDIV_13_WRKING, QINDIV_13_JBAWAY, QINDIV_13_OWNBUS,
            QINDIV_13_RELBUS, QINDIV_13_LOOK4, QINDIV_13_DIFJOB, QINDIV_13_INDOUT, QINDIV_14_WRKING, QINDIV_14_JBAWAY,
            QINDIV_14_OWNBUS, QINDIV_14_RELBUS, QINDIV_14_LOOK4, QINDIV_14_DIFJOB, QINDIV_14_INDOUT, QINDIV_15_WRKING,
            QINDIV_15_JBAWAY, QINDIV_15_OWNBUS, QINDIV_15_RELBUS, QINDIV_15_LOOK4, QINDIV_15_DIFJOB, QINDIV_15_INDOUT,
            QINDIV_16_WRKING, QINDIV_16_JBAWAY, QINDIV_16_OWNBUS, QINDIV_16_RELBUS, QINDIV_16_LOOK4, QINDIV_16_DIFJOB,
            QINDIV_16_INDOUT, INTVNO, BriefSDC1, BriefSDC2, BriefSDC3, BRIEF1 };

    private CSVReader csvReader;
    private Iterator<String[]> csvIterator;
    private ColumnPositionMappingStrategy<LegacySampleEntity> columnPositionMappingStrategy;

    public LegacySampleReader(InputStream stream) {
        columnPositionMappingStrategy = new ColumnPositionMappingStrategy<>();
        columnPositionMappingStrategy.setType(LegacySampleEntity.class);
        columnPositionMappingStrategy.setColumnMapping(SAMPLE_DATA_COLUMNS);
        csvReader = new CSVReader(new InputStreamReader(stream));
        this.setCsvReader(csvReader);
        this.setMappingStrategy(columnPositionMappingStrategy);
    }
}

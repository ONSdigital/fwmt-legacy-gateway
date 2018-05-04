package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class LegacyLFSSampleReaderNew extends LegacyReaderBase<LegacySampleEntity> {
  static final String[] CSV_HEADERS = {
      "SERNO", "TLA", "FP", "Quota_No", "RefDate", "LSTHO", "THISWV", "PREM1", "PREM2", "PREM3", "PREM4", "DISTRICT",
      "POSTTOWN", "POSTCODE", "QUOTA", "ADDR", "OSGRIDREF", "Year", "Month", "MAIN", "NUMHHLD", "HHLDDESC",
      // respondents
      "QRES_LINE_NAME_1", "QRES_LINE_NAME_2", "QRES_LINE_NAME_3", "QRES_LINE_NAME_4", "QRES_LINE_NAME_5",
      "QRES_LINE_NAME_6", "QRES_LINE_NAME_7", "QRES_LINE_NAME_8", "QRES_LINE_NAME_9", "QRES_LINE_NAME_10",
      "QRES_LINE_NAME_11", "QRES_LINE_NAME_12", "QRES_LINE_NAME_13", "QRES_LINE_NAME_14", "QRES_LINE_NAME_15",
      "QRES_LINE_NAME_16",
      "QRES_LINE_AGE_1", "QRES_LINE_AGE_2", "QRES_LINE_AGE_3", "QRES_LINE_AGE_4", "QRES_LINE_AGE_5", "QRES_LINE_AGE_6",
      "QRES_LINE_AGE_7", "QRES_LINE_AGE_8", "QRES_LINE_AGE_9", "QRES_LINE_AGE_10", "QRES_LINE_AGE_11",
      "QRES_LINE_AGE_12", "QRES_LINE_AGE_13", "QRES_LINE_AGE_14", "QRES_LINE_AGE_15", "QRES_LINE_AGE_16",
      "QINDIV_1_WRKING", "QINDIV_1_JBAWAY", "QINDIV_1_OWNBUS", "QINDIV_1_RELBUS", "QINDIV_1_LOOK4", "QINDIV_1_DIFJOB",
      "QINDIV_1_INDOUT",
      "QINDIV_2_WRKING", "QINDIV_2_JBAWAY", "QINDIV_2_OWNBUS", "QINDIV_2_RELBUS", "QINDIV_2_LOOK4", "QINDIV_2_DIFJOB",
      "QINDIV_2_INDOUT",
      "QINDIV_3_WRKING", "QINDIV_3_JBAWAY", "QINDIV_3_OWNBUS", "QINDIV_3_RELBUS", "QINDIV_3_LOOK4", "QINDIV_3_DIFJOB",
      "QINDIV_3_INDOUT",
      "QINDIV_4_WRKING", "QINDIV_4_JBAWAY", "QINDIV_4_OWNBUS", "QINDIV_4_RELBUS", "QINDIV_4_LOOK4", "QINDIV_4_DIFJOB",
      "QINDIV_4_INDOUT",
      "QINDIV_5_WRKING", "QINDIV_5_JBAWAY", "QINDIV_5_OWNBUS", "QINDIV_5_RELBUS", "QINDIV_5_LOOK4", "QINDIV_5_DIFJOB",
      "QINDIV_5_INDOUT",
      "QINDIV_6_WRKING", "QINDIV_6_JBAWAY", "QINDIV_6_OWNBUS", "QINDIV_6_RELBUS", "QINDIV_6_LOOK4", "QINDIV_6_DIFJOB",
      "QINDIV_6_INDOUT",
      "QINDIV_7_WRKING", "QINDIV_7_JBAWAY", "QINDIV_7_OWNBUS", "QINDIV_7_RELBUS", "QINDIV_7_LOOK4", "QINDIV_7_DIFJOB",
      "QINDIV_7_INDOUT",
      "QINDIV_8_WRKING", "QINDIV_8_JBAWAY", "QINDIV_8_OWNBUS", "QINDIV_8_RELBUS", "QINDIV_8_LOOK4", "QINDIV_8_DIFJOB",
      "QINDIV_8_INDOUT",
      "QINDIV_9_WRKING", "QINDIV_9_JBAWAY", "QINDIV_9_OWNBUS", "QINDIV_9_RELBUS", "QINDIV_9_LOOK4", "QINDIV_9_DIFJOB",
      "QINDIV_9_INDOUT",
      "QINDIV_10_WRKING", "QINDIV_10_JBAWAY", "QINDIV_10_OWNBUS", "QINDIV_10_RELBUS", "QINDIV_10_LOOK4",
      "QINDIV_10_DIFJOB", "QINDIV_10_INDOUT",
      "QINDIV_11_WRKING", "QINDIV_11_JBAWAY", "QINDIV_11_OWNBUS", "QINDIV_11_RELBUS", "QINDIV_11_LOOK4",
      "QINDIV_11_DIFJOB", "QINDIV_11_INDOUT",
      "QINDIV_12_WRKING", "QINDIV_12_JBAWAY", "QINDIV_12_OWNBUS", "QINDIV_12_RELBUS", "QINDIV_12_LOOK4",
      "QINDIV_12_DIFJOB", "QINDIV_12_INDOUT",
      "QINDIV_13_WRKING", "QINDIV_13_JBAWAY", "QINDIV_13_OWNBUS", "QINDIV_13_RELBUS", "QINDIV_13_LOOK4",
      "QINDIV_13_DIFJOB", "QINDIV_13_INDOUT",
      "QINDIV_14_WRKING", "QINDIV_14_JBAWAY", "QINDIV_14_OWNBUS", "QINDIV_14_RELBUS", "QINDIV_14_LOOK4",
      "QINDIV_14_DIFJOB", "QINDIV_14_INDOUT",
      "QINDIV_15_WRKING", "QINDIV_15_JBAWAY", "QINDIV_15_OWNBUS", "QINDIV_15_RELBUS", "QINDIV_15_LOOK4",
      "QINDIV_15_DIFJOB", "QINDIV_15_INDOUT",
      "QINDIV_16_WRKING", "QINDIV_16_JBAWAY", "QINDIV_16_OWNBUS", "QINDIV_16_RELBUS", "QINDIV_16_LOOK4",
      "QINDIV_16_DIFJOB", "QINDIV_16_INDOUT",
      "WEEK", "W1YR", "QRTR", "WAVFND", "HHLD", "CHKLET", "DIVADDIND", "MO", "HOUT",
      // "LSTHO",
      "LFSSAMP", "THANKS", "THANKE", "RECPHONE", "COUNTRY", "NUMPER",
      // previous waves
      "COMMENT_1_REFDTE", "COMMENT_1_INTVNO", "COMMENT_1_BriefSDC1", "COMMENT_1_BriefSDC2", "COMMENT_1_BriefSDC3",
      "COMMENT_1_BRIEF1",
      "COMMENT_2_REFDTE", "COMMENT_2_INTVNO", "COMMENT_2_BriefSDC1", "COMMENT_2_BriefSDC2", "COMMENT_2_BriefSDC3",
      "COMMENT_2_BRIEF1",
      "COMMENT_3_REFDTE", "COMMENT_3_INTVNO", "COMMENT_3_BriefSDC1", "COMMENT_3_BriefSDC2", "COMMENT_3_BriefSDC3",
      "COMMENT_3_BRIEF1",
      "COMMENT_4_REFDTE", "COMMENT_4_INTVNO", "COMMENT_4_BriefSDC1", "COMMENT_4_BriefSDC2", "COMMENT_4_BriefSDC3",
      "COMMENT_4_BRIEF1",
      "COMMENT_5_REFDTE", "COMMENT_5_INTVNO", "COMMENT_5_BriefSDC1", "COMMENT_5_BriefSDC2", "COMMENT_5_BriefSDC3",
      "COMMENT_5_BRIEF1",
      "TELENO", "DIRECTION", "Issue_No", "Part", "Auth", "EmployeeNo", "Last_Updated"};

  // TODO
  private static final String[] requiredFields = {};

  public LegacyLFSSampleReaderNew(InputStream stream) throws IOException {
    super(new InputStreamReader(stream));
  }

  @Override String[] getCSVHeaders() {
    return CSV_HEADERS;
  }

  @Override LegacySampleEntity parseRecord(CSVRecord record) {
    for (String fieldName : requiredFields) {
      String field = record.get(fieldName);
      if (field == null) {
        fail(strings, field + " could not be found, but is required");
        return null;
      }
      if (field.length() == 0) {
        fail(strings, field + " was empty, but is required");
        return null;
      }
    }
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
}

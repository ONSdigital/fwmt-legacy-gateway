package uk.gov.ons.fwmt.gateway.utility.readers;

import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LegacyStaffReader extends LegacyReaderBase<LegacyStaffEntity> {
  private static final String[] CSV_HEADERS = {"employeeNo", "authNo", "forename", "surname", "jobTitle", "email",
      "phone", "userType"};

  public LegacyStaffReader(InputStream stream) throws IOException {
    super(new InputStreamReader(stream));
  }

  @Override String[] getCSVHeaders() {
    return CSV_HEADERS;
  }

  @Override LegacyStaffEntity parseRecord(CSVRecord record) {
    LegacyStaffEntity entity = new LegacyStaffEntity();
    entity.setEmployeeNo(record.get("employeeNo"));
    entity.setAuthNo(record.get("authNo"));
    entity.setForename(record.get("forename"));
    entity.setSurname(record.get("surname"));
    entity.setJobTitle(record.get("jobTitle"));
    entity.setEmail(record.get("email"));
    entity.setPhone(record.get("phone"));
    entity.setUserType(record.get("userType"));
    return entity;
  }
}

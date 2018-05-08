package uk.gov.ons.fwmt.gateway.utility.readers;

import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.error.CSVParsingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LegacyStaffReader extends LegacyReaderBase<LegacyStaffEntity> {
  private static final String[] CSV_HEADERS = {
      "Transmission_Date", "EmployeeNo", "intnum", "Title", "firstname", "Surname", "sex", "add1", "add2", "add3",
      "add4", "postcode", "tel", "tel2", "mob_tel", "emerg_tel", "email_add", "GOR", "county", "grid_ref", "Easting",
      "Northing", "fieldmanager", "Manager_EmployeeNo", "Manager_Auth", "Manager_Firstname", "Manager_Surname",
      "Region", "regionName", "RM_EmployeeNo", "RM_Firstname", "RM_Surname"
  };

  private static final String[] requiredFields = {"Transmission_Date", "Title", "firstname", "Surname", "add1",
      "postcode", "EmployeeNo"};

  public LegacyStaffReader(InputStream stream) throws IOException {
    super(new InputStreamReader(stream));
  }

  @Override String[] getCSVHeaders() {
    return CSV_HEADERS;
  }

  @Override LegacyStaffEntity parseRecord(CSVRecord record) throws CSVParsingException {
    for (String fieldName : requiredFields) {
      String field = record.get(fieldName);
      if (field == null) {
        List<String> rows = new ArrayList<>();
        record.iterator().forEachRemaining(rows::add);
        throw new CSVParsingException(field + " could not be found, but is required", rows);
      }
      if (field.length() == 0) {
        List<String> rows = new ArrayList<>();
        record.iterator().forEachRemaining(rows::add);
        throw new CSVParsingException(field + " could not be found, but is required", rows);
      }
    }
    LegacyStaffEntity entity = new LegacyStaffEntity();
    entity.setEmployeeNo(record.get("EmployeeNo"));
    // TODO THIS IS TEMPORARY, IT SHOULD READ 'Auth'
    entity.setAuthNo(record.get("intnum"));
    entity.setForename(record.get("firstname"));
    entity.setSurname(record.get("Surname"));
    entity.setJobTitle("Interviewer");
    entity.setEmail(record.get("email_add"));
    entity.setPhone(record.get("tel"));
    // TODO What is this?
    entity.setUserType("?");
    return entity;
  }
}

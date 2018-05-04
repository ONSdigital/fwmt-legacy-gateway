package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class LegacyGFFSampleReaderNew extends LegacyReaderBase<LegacySampleEntity> {
  static final String[] CSV_HEADERS = {"Serno", "TLA", "Stage", "Wave", "Prem1", "Prem2", "Prem3", "Prem4", "District",
      "PostTown", "Postcode", "Quota", "AddressNo", "OSGridRef", "Year", "Month", "LAUA", "LAUA_Name", "SubSample",
      "Rand", "ADULT2", "ADULT3", "ADULT4", "ADULT5", "ADULT6", "ADULT7", "ADULT8", "ADULT9", "ADULT10", "ADULT11",
      "ADULT12", "ADULT13", "ADULT14", "Telno", "Issue_No", "Part", "Auth", "EmployeeNo", "Last_Updated", "Ward",
      "Ward_Name", "MO", "DiffAddInd", "GFFMU", "OldSerial"};
  private static final String[] requiredFields = {"Serno", "TLA", "Stage", "Quota", "Auth", "EmployeeNo", "Prem1",
      "District", "PostTown", "Postcode", "AddressNo", "OSGridRef"};

  public LegacyGFFSampleReaderNew(InputStream stream) throws IOException {
    super(new InputStreamReader(stream));
  }

  @Override String[] getCSVHeaders() {
    return CSV_HEADERS;
  }

  @Override LegacySampleEntity parseRecord(CSVRecord record) {
    for (String fieldName : requiredFields) {
      String field = record.get(field);
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
    entity.setSerno(record.get("Serno"));
    entity.setTla(record.get("TLA"));
    entity.setFp(record.get("Stage"));
    entity.setQuota(record.get("Quota"));
    entity.setAuthNo(record.get("Auth"));
    entity.setEmployeeNo(record.get("EmployeeNo"));
    entity.setAddressLine1(record.get("Prem1"));
    entity.setAddressLine2(record.get("Prem2"));
    entity.setAddressLine3(record.get("Prem3"));
    entity.setAddressLine4(record.get("Prem4"));
    entity.setDistrict(record.get("District"));
    entity.setPostTown(record.get("PostTown"));
    entity.setPostcode(record.get("Postcode"));
    entity.setAddressNo(record.get("AddressNo"));
    entity.setOsGridRef(record.get("OSGridRef"));
    entity.setAdult2(record.get("ADULT2"));
    entity.setAdult3(record.get("ADULT3"));
    entity.setAdult4(record.get("ADULT4"));
    entity.setAdult5(record.get("ADULT5"));
    entity.setAdult6(record.get("ADULT6"));
    entity.setAdult7(record.get("ADULT7"));
    entity.setAdult8(record.get("ADULT8"));
    entity.setAdult9(record.get("ADULT9"));
    entity.setAdult10(record.get("ADULT10"));
    entity.setAdult11(record.get("ADULT11"));
    entity.setAdult12(record.get("ADULT12"));
    entity.setAdult13(record.get("ADULT13"));
    entity.setAdult14(record.get("ADULT14"));
    return entity;
  }
}

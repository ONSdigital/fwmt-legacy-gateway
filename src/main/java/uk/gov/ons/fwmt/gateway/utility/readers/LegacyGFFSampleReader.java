package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.error.CSVParsingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LegacyGFFSampleReader extends LegacyReaderBase<LegacySampleEntity> {
  private static final String[] CSV_HEADERS = {
      "Transmission_Date", "Serno", "TLA", "Year", "Month", "Stage", "Wave", "Name", "Prem1", "Prem2", "Prem3", "Prem4",
      "District", "PostTown", "Postcode", "Quota", "AddressNo", "OSGridRef", "Issue_No", "Part", "Auth", "EmployeeNo",
      "Last_Updated", "Rand", "LAUA", "LAUA_Name", "Ward", "Ward_Name", "MO", "DivAddInd", "GFFMU", "OldSerial",
      "SubSample",
      "ADULT2", "ADULT3", "ADULT4", "ADULT5", "ADULT6", "ADULT7", "ADULT8", "ADULT9", "ADULT10", "ADULT11",
      "ADULT12", "ADULT13", "ADULT14"
      // missing in the provided samples
      // "Telno"
  };

  private static final String[] requiredFields = {"Transmission_Date", "Serno", "TLA", "Stage", "Wave", "Prem1",
      "Postcode", "Auth", "EmployeeNo"};

  public LegacyGFFSampleReader(InputStream stream) throws IOException {
    super(new InputStreamReader(stream));
  }

  @Override String[] getCSVHeaders() {
    return CSV_HEADERS;
  }

  @Override LegacySampleEntity parseRecord(CSVRecord record) throws CSVParsingException {
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

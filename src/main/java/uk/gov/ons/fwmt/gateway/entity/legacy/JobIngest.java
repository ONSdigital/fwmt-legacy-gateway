package uk.gov.ons.fwmt.gateway.entity.legacy;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class JobIngest {
  // All: taken from the 'TransmissionDate' field
  // TODO is this 'Transmission_Date'?
  // mandatory
  private final String timestamp;

  // GFF: taken from the 'Serno' field
  // LFS: taken from the 'SERNO' field
  // mandatory
  private final String serNo;

  // All: taken from the 'TLA' field
  // mandatory
  private final String tla;

  // GFF: taken from the 'Stage' field
  // LFS: taken from the 'FP' field
  // mandatory
  private final String stage;

  // GFF: taken from the 'Wave' field
  // LFS: taken from the 'THISWV' field
  // mandatory
  private final String wave;

  // GFF: taken from the 'Prem1' field
  // LFS: taken from the 'PREM1' field
  // mandatory
  private final String addressLine1;

  // GFF: taken from the 'Prem2' field
  // LFS: taken from the 'PREM2' field
  private final String addressLine2;

  // GFF: taken from the 'Prem3' field
  // LFS: taken from the 'PREM3' field
  private final String addressLine3;

  // GFF: taken from the 'Prem4' field
  // LFS: taken from the 'PREM4' field
  private final String addressLine4;

  // GFF: taken from the 'District' field
  // LFS: taken from the 'DISTRICT' field
  private final String district;

  // GFF: taken from the 'PostTown' field
  // LFS: taken from the 'POSTTOWN' field
  private final String postTown;

  // GFF: taken from the 'Postcode' field
  // LFS: taken from the 'POSTCODE' field
  // mandatory
  private final String postcode;

  // GFF: taken from the 'Quota' field
  // LFS: taken from the 'QUOTA' field
  private final String quota;

  // GFF: taken from the 'AddressNo' field
  // LFS: taken from the 'ADDR' field
  private final String addressNo;

  // GFF: taken from the 'OSGridRef' field
  // LFS: taken from the 'OSGRIDREF' field
  private final String osGridRef;

  // All: taken from the 'Year' field
  private final String year;

  // All: taken from the 'Month' field
  private final String month;

  // GFF: taken from the 'Telno' field
  // LFS: taken from the 'TELNO' field
  private final String telNo;

  // All: taken from the 'Issue_No' field
  private final String issueNo;

  // All: taken from the 'Part' field
  private final String part;

  // All: taken from the 'EmployeeNo' field
  // mandatory
  private final String employeeNo;

  // All: taken from the 'Auth' field
  // mandatory
  private final String auth;

  // All: taken from the 'Last_Updated' field
  private final String lastUpdated;

  // Data that is specific surveys
  private final SurveyType surveyType;
  private final JobGFFDataIngest gffData;
  private final JobLFSDataIngest lfsData;

  // // // TODO what are these?
  public String tmJobId;
  public String legacyJobId;

  public JobIngest(CSVRecord record, SurveyType surveyType) {
    this.timestamp = record.get("TransmissionDate");
    this.tla = record.get("TLA");
    this.year = (record.isSet("Year")) ? record.get("Year") : null;
    this.month = (record.isSet("Month")) ? record.get("Month") : null;
    this.issueNo = (record.isSet("Issue_No")) ? record.get("Issue_No") : null;
    this.part = (record.isSet("Part")) ? record.get("Part") : null;
    this.employeeNo = record.get("EmployeeNo");
    this.auth = record.get("Auth");
    this.lastUpdated = (record.isSet("Last_Updated")) ? record.get("Last_Updated") : null;

    switch (surveyType) {
    case GFF:
      this.serNo = record.get("Serno");
      this.stage = record.get("Stage");
      this.wave = record.get("Wave");
      this.addressLine1 = record.get("Prem1");
      this.addressLine2 = (record.isSet("Prem2")) ? record.get("Prem2") : null;
      this.addressLine3 = (record.isSet("Prem3")) ? record.get("Prem3") : null;
      this.addressLine4 = (record.isSet("Prem4")) ? record.get("Prem4") : null;
      this.district = (record.isSet("District")) ? record.get("District") : null;
      this.postTown = (record.isSet("PostTown")) ? record.get("PostTown") : null;
      this.postcode = record.get("Postcode");
      this.quota = (record.isSet("Quota")) ? record.get("Quota") : null;
      this.addressNo = (record.isSet("AddressNo")) ? record.get("AddressNo") : null;
      this.osGridRef = (record.isSet("OSGridRef")) ? record.get("OSGridRef") : null;
      this.telNo = (record.isSet("Telno")) ? record.get("Telno") : null;

      this.surveyType = SurveyType.GFF;
      this.gffData = new JobGFFDataIngest(record);
      this.lfsData = null;

      break;
    case LFS:
      this.serNo = record.get("SERNO");
      this.stage = record.get("FP");
      this.wave = record.get("THISWV");
      this.addressLine1 = record.get("PREM1");
      this.addressLine2 = (record.isSet("PREM2")) ? record.get("PREM2") : null;
      this.addressLine3 = (record.isSet("PREM3")) ? record.get("PREM3") : null;
      this.addressLine4 = (record.isSet("PREM4")) ? record.get("PREM4") : null;
      this.district = (record.isSet("DISTRICT")) ? record.get("DISTRICT") : null;
      this.postTown = (record.isSet("POSTTOWN")) ? record.get("POSTTOWN") : null;
      this.postcode = record.get("POSTCODE");
      this.quota = (record.isSet("QUOTA")) ? record.get("QUOTA") : null;
      this.addressNo = (record.isSet("ADDR")) ? record.get("ADDR") : null;
      this.osGridRef = (record.isSet("OSGRIDREF")) ? record.get("OSGRIDREF") : null;
      this.telNo = (record.isSet("TELNO")) ? record.get("TELNO") : null;

      this.surveyType = SurveyType.LFS;
      this.gffData = null;
      this.lfsData = new JobLFSDataIngest(record);

      break;
    default:
      throw new IllegalArgumentException("Unknown survey type");
    }
  }
}

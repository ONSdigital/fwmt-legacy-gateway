package uk.gov.ons.fwmt.gateway.entity.legacy.csv;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class JobIngest {
  // All: taken from the 'TransmissionDate' field
  // TODO is this 'Transmission_Date'?
  private final String timestamp;

  // GFF: taken from the 'Serno' field
  // LFS: taken from the 'SERNO' field
  private final String serNo;

  // All: taken from the 'TLA' field
  private final String tla;

  // GFF: taken from the 'Stage' field
  // LFS: taken from the 'FP' field
  private final String stage;

  // GFF: taken from the 'Wave' field
  // LFS: taken from the 'THISWV' field
  private final String wave;

  // GFF: taken from the 'Prem1' field
  // LFS: taken from the 'PREM1' field
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
  private final String employeeNo;

  // All: taken from the 'Auth' field
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
    this.year = record.get("Year");
    this.month = record.get("Month");
    this.issueNo = record.get("Issue_No");
    this.part = record.get("Part");
    this.employeeNo = record.get("EmployeeNo");
    this.auth = record.get("Auth");
    this.lastUpdated = record.get("Last_Updated");

    switch (surveyType) {
    case GFF:
      this.serNo = record.get("Serno");
      this.stage = record.get("Stage");
      this.wave = record.get("Wave");
      this.addressLine1 = record.get("Prem1");
      this.addressLine2 = record.get("Prem2");
      this.addressLine3 = record.get("Prem3");
      this.addressLine4 = record.get("Prem4");
      this.district = record.get("District");
      this.postTown = record.get("PostTown");
      this.postcode = record.get("Postcode");
      this.quota = record.get("Quota");
      this.addressNo = record.get("AddressNo");
      this.osGridRef = record.get("OSGridRef");
      this.telNo = record.get("Telno");

      this.surveyType = SurveyType.GFF;
      this.gffData = new JobGFFDataIngest(record);
      this.lfsData = null;

      break;
    case LFS:
      this.serNo = record.get("SERNO");
      this.stage = record.get("FP");
      this.wave = record.get("THISWV");
      this.addressLine1 = record.get("PREM1");
      this.addressLine2 = record.get("PREM2");
      this.addressLine3 = record.get("PREM3");
      this.addressLine4 = record.get("PREM4");
      this.district = record.get("DISTRICT");
      this.postTown = record.get("POSTTOWN");
      this.postcode = record.get("POSTCODE");
      this.quota = record.get("QUOTA");
      this.addressNo = record.get("ADDR");
      this.osGridRef = record.get("OSGRIDREF");
      this.telNo = record.get("TELNO");

      this.surveyType = SurveyType.LFS;
      this.gffData = null;
      this.lfsData = new JobLFSDataIngest(record);

      break;
    default:
      throw new IllegalArgumentException("Unknown survey type");
    }
  }
}

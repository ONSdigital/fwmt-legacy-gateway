package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVColumn;

@Data
public class LegacySampleIngest {
  // TODO is this 'Transmission_Date'?
  @CSVColumn(value = "TransmissionDate", mandatory = true)
  private final String timestamp;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Serno", when = "GFF"),
    @CSVColumn.Mapping(value = "SERNO", when = "LFS"),
  }, mandatory = true)
  private final String serNo;

  @CSVColumn(value = "TLA", mandatory = true)
  private final String tla;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Stage", when = "GFF"),
    @CSVColumn.Mapping(value = "FP", when = "LFS"),
  }, mandatory = true)
  private final String stage;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Wave", when = "GFF"),
    @CSVColumn.Mapping(value = "THISWV", when = "LFS"),
  }, mandatory = true)
  private final String wave;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Prem1", when = "GFF"),
    @CSVColumn.Mapping(value = "PREM1", when = "LFS"),
  }, mandatory = true)
  private final String addressLine1;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Prem2", when = "GFF"),
    @CSVColumn.Mapping(value = "PREM2", when = "LFS"),
  })
  private final String addressLine2;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Prem3", when = "GFF"),
    @CSVColumn.Mapping(value = "PREM3", when = "LFS"),
  })
  private final String addressLine3;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Prem4", when = "GFF"),
    @CSVColumn.Mapping(value = "PREM4", when = "LFS"),
  })
  private final String addressLine4;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "District", when = "GFF"),
    @CSVColumn.Mapping(value = "DISTRICT", when = "LFS"),
  })
  private final String district;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "PostTown", when = "GFF"),
    @CSVColumn.Mapping(value = "POSTTOWN", when = "LFS"),
  })
  private final String postTown;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Postcode", when = "GFF"),
    @CSVColumn.Mapping(value = "POSTCODE", when = "LFS"),
  }, mandatory = true)
  private final String postcode;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Quota", when = "GFF"),
    @CSVColumn.Mapping(value = "QUOTA", when = "LFS"),
  })
  private final String quota;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "AddressNo", when = "GFF"),
    @CSVColumn.Mapping(value = "ADDR", when = "LFS"),
  })
  private final String addressNo;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "OSGridRef", when = "GFF"),
    @CSVColumn.Mapping(value = "OSGRIDREF", when = "LFS"),
  })
  private final String osGridRef;

  @CSVColumn("Year")
  private final String year;

  @CSVColumn("Month")
  private final String month;

  @CSVColumn(values = {
    @CSVColumn.Mapping(value = "Telno", when = "GFF"),
    @CSVColumn.Mapping(value = "TELNO", when = "LFS"),
  })
  private final String telNo;

  @CSVColumn("Issue_No")
  private final String issueNo;

  @CSVColumn("Part")
  private final String part;

  @CSVColumn(value = "EmployeeNo", mandatory = true)
  private final String employeeNo;

  @CSVColumn(value = "Auth", mandatory = true)
  private final String auth;

  @CSVColumn("Last_Updated")
  private final String lastUpdated;

  // Data that is specific surveys
  private final LegacySampleSurveyType legacySampleSurveyType;
  private final LegacySampleGFFDataIngest gffData;
  private final LegacySampleLFSDataIngest lfsData;

  // // // TODO what are these?
  public String tmJobId;
  public String legacyJobId;

  public LegacySampleIngest(CSVRecord record, LegacySampleSurveyType legacySampleSurveyType) {
    this.timestamp = record.get("TransmissionDate");
    this.tla = record.get("TLA");
    this.year = (record.isSet("Year")) ? record.get("Year") : null;
    this.month = (record.isSet("Month")) ? record.get("Month") : null;
    this.issueNo = (record.isSet("Issue_No")) ? record.get("Issue_No") : null;
    this.part = (record.isSet("Part")) ? record.get("Part") : null;
    this.employeeNo = record.get("EmployeeNo");
    this.auth = record.get("Auth");
    this.lastUpdated = (record.isSet("Last_Updated")) ? record.get("Last_Updated") : null;

    switch (legacySampleSurveyType) {
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

      this.legacySampleSurveyType = LegacySampleSurveyType.GFF;
      this.gffData = new LegacySampleGFFDataIngest(record);
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

      this.legacySampleSurveyType = LegacySampleSurveyType.LFS;
      this.gffData = null;
      this.lfsData = new LegacySampleLFSDataIngest(record);

      break;
    default:
      throw new IllegalArgumentException("Unknown survey type");
    }
  }
}

package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.JobAdditionalProperty;

import java.util.Date;

@Data
@NoArgsConstructor
public class LegacySampleIngest {
  // TODO should this be 'TransmissionDate'?
  @CSVColumn(value = "Transmission_Date", mandatory = true)
  private String timestamp;

  // TODO should this be 'SERNO' for LFS?
  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Serno", when = "GFF"),
      @CSVColumn.Mapping(value = "Serno", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("serno")
  private String serNo;

  @CSVColumn(value = "TLA", mandatory = true)
  @JobAdditionalProperty("TLA")
  private String tla;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Stage", when = "GFF"),
      @CSVColumn.Mapping(value = "FP", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("week")
  private String stage;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Wave", when = "GFF"),
      @CSVColumn.Mapping(value = "THISWV", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("wave")
  private String wave;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem1", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM1", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("prem1")
  private String addressLine1;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem2", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM2", when = "LFS"),
  })
  @JobAdditionalProperty("prem2")
  private String addressLine2;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem3", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM3", when = "LFS"),
  })
  @JobAdditionalProperty("prem3")
  private String addressLine3;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem4", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM4", when = "LFS"),
  })
  @JobAdditionalProperty("prem4")
  private String addressLine4;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "District", when = "GFF"),
      @CSVColumn.Mapping(value = "DISTRICT", when = "LFS"),
  })
  @JobAdditionalProperty("district")
  private String district;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "PostTown", when = "GFF"),
      @CSVColumn.Mapping(value = "POSTTOWN", when = "LFS"),
  })
  @JobAdditionalProperty("postTown")
  private String postTown;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Postcode", when = "GFF"),
      @CSVColumn.Mapping(value = "POSTCODE", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("postCode")
  private String postcode;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Quota", when = "GFF"),
      @CSVColumn.Mapping(value = "QUOTA", when = "LFS"),
  })
  @JobAdditionalProperty("quotaNo")
  private String quota;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "AddressNo", when = "GFF"),
      @CSVColumn.Mapping(value = "ADDR", when = "LFS"),
  })
  @JobAdditionalProperty("addressNo")
  private String addressNo;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "OSGridRef", when = "GFF"),
      @CSVColumn.Mapping(value = "OSGRIDREF", when = "LFS"),
  })
  private String osGridRef;

  @CSVColumn("Year")
  @JobAdditionalProperty("year")
  private String year;

  @CSVColumn("Month")
  @JobAdditionalProperty("month")
  private String month;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Telno", when = "GFF"),
      @CSVColumn.Mapping(value = "TELENO", when = "LFS"),
  })
  @JobAdditionalProperty("contactNo")
  private String telNo;

  @CSVColumn("Issue_No")
  private String issueNo;

  @CSVColumn("Part")
  private String part;

  @CSVColumn(value = "EmployeeNo", mandatory = true)
  private String employeeNo;

  @CSVColumn(value = "Auth", mandatory = true)
  private String auth;

  @CSVColumn("Last_Updated")
  private String lastUpdated;

  // Data that is specific surveys
  private LegacySampleSurveyType legacySampleSurveyType;
  private LegacySampleGFFDataIngest gffData;
  private LegacySampleLFSDataIngest lfsData;

  // // // Derived Fields
  // These are fields derived from the contents of the CSV, but do not map to a
  // specific column

  private String tmJobId;
  private Date dueDate;

  @JobAdditionalProperty("geoX")
  private Float geoX;

  @JobAdditionalProperty("geoY")
  private Float geoY;

  public boolean isGffReissue() {
    return (this.getLegacySampleSurveyType() == LegacySampleSurveyType.GFF) &&
        (Integer.parseInt(this.getStage().substring(1, 3)) > 12);
  }
}

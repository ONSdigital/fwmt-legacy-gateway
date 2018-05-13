package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.JobAdditionalProperty;
import uk.gov.ons.fwmt.legacy_gateway.error.CSVParsingException;

import java.util.Calendar;
import java.util.Date;

@Data
public class LegacySampleIngest {
  // TODO should this be 'TransmissionDate'?
  @CSVColumn(value = "Transmission_Date", mandatory = true)
  private final String timestamp;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Serno", when = "GFF"),
      @CSVColumn.Mapping(value = "SERNO", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("serno")
  private final String serNo;

  @CSVColumn(value = "TLA", mandatory = true)
  @JobAdditionalProperty("TLA")
  private final String tla;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Stage", when = "GFF"),
      @CSVColumn.Mapping(value = "FP", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("week")
  private final String stage;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Wave", when = "GFF"),
      @CSVColumn.Mapping(value = "THISWV", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("wave")
  private final String wave;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem1", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM1", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("prem1")
  private final String addressLine1;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem2", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM2", when = "LFS"),
  })
  @JobAdditionalProperty("prem2")
  private final String addressLine2;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem3", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM3", when = "LFS"),
  })
  @JobAdditionalProperty("prem3")
  private final String addressLine3;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Prem4", when = "GFF"),
      @CSVColumn.Mapping(value = "PREM4", when = "LFS"),
  })
  @JobAdditionalProperty("prem4")
  private final String addressLine4;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "District", when = "GFF"),
      @CSVColumn.Mapping(value = "DISTRICT", when = "LFS"),
  })
  @JobAdditionalProperty("district")
  private final String district;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "PostTown", when = "GFF"),
      @CSVColumn.Mapping(value = "POSTTOWN", when = "LFS"),
  })
  @JobAdditionalProperty("postTown")
  private final String postTown;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Postcode", when = "GFF"),
      @CSVColumn.Mapping(value = "POSTCODE", when = "LFS"),
  }, mandatory = true)
  @JobAdditionalProperty("postCode")
  private final String postcode;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Quota", when = "GFF"),
      @CSVColumn.Mapping(value = "QUOTA", when = "LFS"),
  })
  @JobAdditionalProperty("quotaNo")
  private final String quota;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "AddressNo", when = "GFF"),
      @CSVColumn.Mapping(value = "ADDR", when = "LFS"),
  })
  @JobAdditionalProperty("addressNo")
  private final String addressNo;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "OSGridRef", when = "GFF"),
      @CSVColumn.Mapping(value = "OSGRIDREF", when = "LFS"),
  })
  private final String osGridRef;

  @CSVColumn("Year")
  @JobAdditionalProperty("year")
  private final String year;

  @CSVColumn("Month")
  @JobAdditionalProperty("month")
  private final String month;

  @CSVColumn(values = {
      @CSVColumn.Mapping(value = "Telno", when = "GFF"),
      @CSVColumn.Mapping(value = "TELNO", when = "LFS"),
  })
  @JobAdditionalProperty("contactNo")
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

  // // // Derived Fields
  // These are fields derived from the contents of the CSV, but do not map to a specific column

  public final String tmJobId;
  public String legacyJobId;
  public final Date dueDate;

  @JobAdditionalProperty("geoX")
  public final Float geoX;

  @JobAdditionalProperty("geoY")
  public final Float geoY;

  protected static String constructTmJobId(CSVRecord record, LegacySampleSurveyType surveyType) {
    switch (surveyType) {
    case GFF:
      // quota '-' addr '-' stage
      return record.get("Quota") + "-" + record.get("AddressNo") + "-" + record.get("Stage");
    case LFS:
      // quota week w1yr qrtr addr wavfnd hhld chklet
      return record.get("QUOTA") + record.get("WEEK") + record.get("W1YR") + record.get("QRTR") + record.get("ADDR")
          + record.get("WAVFND") + record.get("HHLD") + record.get("CHKLET");
    default:
      throw new IllegalArgumentException("Invalid survey type");
    }
  }

  protected static Date convertToGFFDate(String stage) {
    int year = Integer.parseInt(stage.substring(0, 1));
    int month = Integer.parseInt(stage.substring(1, 3));
    if (month > 12) {
      month = month - 20;
    }
    Calendar cal = Calendar.getInstance();
    cal.set(2010 + year, month - 1, 1);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
    cal.set(Calendar.HOUR, 11);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.AM_PM, Calendar.PM);
    return cal.getTime();
  }

  // technically, 'stage' here is the field period 'fp'
  protected static Date convertToLFSDate(String stage) {
    int year = Integer.parseInt(stage.substring(0, 1));
    int quarter = Integer.parseInt(stage.substring(1, 2));
    int week = stage.toLowerCase().charAt(2) - 'a' + 3;
    Calendar cal = Calendar.getInstance();
    cal.set(2010 + year, 1 + (3 * (quarter - 1)) - 1, 1);
    cal.set(Calendar.HOUR, 11);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.AM_PM, Calendar.PM);
    cal.add(Calendar.DATE, (7 * (week)) - 1);
    return cal.getTime();
  }

  public LegacySampleIngest(CSVRecord record, LegacySampleSurveyType surveyType) {
    this.timestamp = record.get("Transmission_Date");
    this.tla = record.get("TLA");
    this.year = (record.isSet("Year")) ? record.get("Year") : null;
    this.month = (record.isSet("Month")) ? record.get("Month") : null;
    this.issueNo = (record.isSet("Issue_No")) ? record.get("Issue_No") : null;
    this.part = (record.isSet("Part")) ? record.get("Part") : null;
    this.employeeNo = record.get("EmployeeNo");
    this.auth = record.get("Auth");
    this.lastUpdated = (record.isSet("Last_Updated")) ? record.get("Last_Updated") : null;

    this.tmJobId = constructTmJobId(record, surveyType);

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

      this.legacySampleSurveyType = LegacySampleSurveyType.GFF;
      this.gffData = new LegacySampleGFFDataIngest(record);
      this.lfsData = null;

      this.dueDate = convertToGFFDate(this.stage);

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

      this.dueDate = convertToLFSDate(this.stage);

      break;
    default:
      throw new IllegalArgumentException("Unknown survey type");
    }

    // derive the coordinates
    if (this.osGridRef != null) {
      // TODO Confirm this is correct with new data map
      String[] osGridRefSplit = this.getOsGridRef().split(",", 1);
      if (osGridRefSplit.length != 2) {
        throw new IllegalArgumentException("OS Grid Reference was not in the expected format");
      }
      this.geoX = Float.parseFloat(osGridRefSplit[0]);
      this.geoY = Float.parseFloat(osGridRefSplit[1]);
    } else {
      this.geoX = null;
      this.geoY = null;
    }
  }

  public boolean isGffReissue() {
    return (this.getLegacySampleSurveyType() == LegacySampleSurveyType.GFF) &&
        (Integer.parseInt(this.getStage().substring(1, 2)) > 12);
  }

}

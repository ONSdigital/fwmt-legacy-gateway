package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;

@Data
@NoArgsConstructor
public class LegacySampleGFFDataIngest {
  // TODO should this be 'name'?
  @CSVColumn("Name")
  private String name;

  @CSVColumn("LAUA")
  private String laua;

  @CSVColumn("LAUA_Name")
  private String lauaName;

  @CSVColumn("SubSample")
  private String subSample;

  @CSVColumn("Rand")
  private String rand;

  @CSVColumn("ADULT2")
  private String adult2;

  @CSVColumn("ADULT3")
  private String adult3;

  @CSVColumn("ADULT4")
  private String adult4;

  @CSVColumn("ADULT5")
  private String adult5;

  @CSVColumn("ADULT6")
  private String adult6;

  @CSVColumn("ADULT7")
  private String adult7;

  @CSVColumn("ADULT8")
  private String adult8;

  @CSVColumn("ADULT9")
  private String adult9;

  @CSVColumn("ADULT10")
  private String adult10;

  @CSVColumn("ADULT11")
  private String adult11;

  @CSVColumn("ADULT12")
  private String adult12;

  @CSVColumn("ADULT13")
  private String adult13;

  @CSVColumn("ADULT14")
  private String adult14;

  @CSVColumn(value = "Ward", ignored = true)
  private String ward;

  @CSVColumn(value = "Ward_Name", ignored = true)
  private String wardName;

  @CSVColumn(value = "MO", ignored = true)
  private String mo;

  @CSVColumn(value = "DivAddInd", ignored = true)
  private String divAddInd;

  @CSVColumn(value = "GFFMU", ignored = true)
  private String gffmu;

  @CSVColumn("OldSerial")
  private String oldSerial;

}

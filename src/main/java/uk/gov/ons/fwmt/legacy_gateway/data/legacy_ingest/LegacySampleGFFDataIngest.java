package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;
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

  //@CSVColumn(value = "Ward", ignored = true)
  //private String ward;

  //@CSVColumn(value = "Ward_Name", ignored = true)
  //private String wardName;

  //@CSVColumn(value = "MO", ignored = true)
  //private String mo;

  //@CSVColumn(value = "DivAddInd", ignored = true)
  //private String divAddInd;

  //@CSVColumn(value = "GFFMU", ignored = true)
  //private String gffmu;

  @CSVColumn("OldSerial")
  private String oldSerial;

//  public LegacySampleGFFDataIngest(CSVRecord record) {
//    this.name = (record.isSet("Name")) ? record.get("Name") : null;
//    this.laua = (record.isSet("LAUA")) ? record.get("LAUA") : null;
//    this.lauaName = (record.isSet("LAUA_Name")) ? record.get("LAUA_Name") : null;
//    this.subSample = (record.isSet("SubSample")) ? record.get("SubSample") : null;
//    this.rand = (record.isSet("Rand")) ? record.get("Rand") : null;
//    this.adult2 = (record.isSet("ADULT2")) ? record.get("ADULT2") : null;
//    this.adult3 = (record.isSet("ADULT3")) ? record.get("ADULT3") : null;
//    this.adult4 = (record.isSet("ADULT4")) ? record.get("ADULT4") : null;
//    this.adult5 = (record.isSet("ADULT5")) ? record.get("ADULT5") : null;
//    this.adult6 = (record.isSet("ADULT6")) ? record.get("ADULT6") : null;
//    this.adult7 = (record.isSet("ADULT7")) ? record.get("ADULT7") : null;
//    this.adult8 = (record.isSet("ADULT8")) ? record.get("ADULT8") : null;
//    this.adult9 = (record.isSet("ADULT9")) ? record.get("ADULT9") : null;
//    this.adult10 = (record.isSet("ADULT10")) ? record.get("ADULT10") : null;
//    this.adult11 = (record.isSet("ADULT11")) ? record.get("ADULT11") : null;
//    this.adult12 = (record.isSet("ADULT12")) ? record.get("ADULT12") : null;
//    this.adult13 = (record.isSet("ADULT13")) ? record.get("ADULT13") : null;
//    this.adult14 = (record.isSet("ADULT14")) ? record.get("ADULT14") : null;
//    //this.ward = (record.isSet("Ward")) ? record.get("Ward") : null;
//    //this.wardName = (record.isSet("Ward_Name")) ? record.get("Ward_Name") : null;
//    //this.mo = (record.isSet("MO")) ? record.get("MO") : null;
//    //this.divAddInd = (record.isSet("DivAddInd")) ? record.get("DivAddInd") : null;
//    //this.gffmu = (record.isSet("GFFMU")) ? record.get("GFFMU") : null;
//    this.oldSerial = (record.isSet("OldSerial")) ? record.get("OldSerial") : null;
//  }

}

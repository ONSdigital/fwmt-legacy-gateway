package uk.gov.ons.fwmt.gateway.entity.legacy.csv;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

@Data
public class JobGFFDataIngest {
  // taken from the 'name' field
  // TODO is this 'Name'?
  private final String name;

  // taken from the 'LAUA' field
  private final String laua;

  // taken from the 'LAUA_Name' field
  private final String lauaName;

  // taken from the 'SubSample' field
  private final String subSample;

  // taken from the 'Rand' field
  private final String rand;

  // taken from the 'ADULT2' field
  private final String adult2;

  // taken from the 'ADULT3' field
  private final String adult3;

  // taken from the 'ADULT4' field
  private final String adult4;

  // taken from the 'ADULT5' field
  private final String adult5;

  // taken from the 'ADULT6' field
  private final String adult6;

  // taken from the 'ADULT7' field
  private final String adult7;

  // taken from the 'ADULT8' field
  private final String adult8;

  // taken from the 'ADULT9' field
  private final String adult9;

  // taken from the 'ADULT10' field
  private final String adult10;

  // taken from the 'ADULT11' field
  private final String adult11;

  // taken from the 'ADULT12' field
  private final String adult12;

  // taken from the 'ADULT13' field
  private final String adult13;

  // taken from the 'ADULT14' field
  private final String adult14;

  // taken from the 'Ward' field
  // ignored
  //private final String ward;

  // taken from the 'Ward_Name' field
  // ignored
  //private final String wardName;

  // taken from the 'MO' field
  // ignored
  //private final String mo;

  // taken from the 'DivAddInd' field
  // ignored
  //private final String divAddInd;

  // taken from the 'GFFMU' field
  // ignored
  //private final String gffmu;

  // taken from the 'OldSerial' field
  private final String oldSerial;

  public JobGFFDataIngest(CSVRecord record) {
    this.name = record.get("name");
    this.laua = record.get("LAUA");
    this.lauaName = record.get("LAUA_Name");
    this.subSample = record.get("SubSample");
    this.rand = record.get("Rand");
    this.adult2 = record.get("ADULT2");
    this.adult3 = record.get("ADULT3");
    this.adult4 = record.get("ADULT4");
    this.adult5 = record.get("ADULT5");
    this.adult6 = record.get("ADULT6");
    this.adult7 = record.get("ADULT7");
    this.adult8 = record.get("ADULT8");
    this.adult9 = record.get("ADULT9");
    this.adult10 = record.get("ADULT10");
    this.adult11 = record.get("ADULT11");
    this.adult12 = record.get("ADULT12");
    this.adult13 = record.get("ADULT13");
    this.adult14 = record.get("ADULT14");
    //this.ward = record.get("Ward");
    //this.wardName = record.get("Ward_Name");
    //this.mo = record.get("MO");
    //this.divAddInd = record.get("DivAddInd");
    //this.gffmu = record.get("GFFMU");
    this.oldSerial = record.get("OldSerial");
  }

}

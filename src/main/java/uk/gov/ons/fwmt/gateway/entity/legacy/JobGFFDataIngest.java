package uk.gov.ons.fwmt.gateway.entity.legacy;

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
    this.name = (record.isSet("name")) ? record.get("name") : null;
    this.laua = (record.isSet("LAUA")) ? record.get("LAUA") : null;
    this.lauaName = (record.isSet("LAUA_Name")) ? record.get("LAUA_Name") : null;
    this.subSample = (record.isSet("SubSample")) ? record.get("SubSample") : null;
    this.rand = (record.isSet("Rand")) ? record.get("Rand") : null;
    this.adult2 = (record.isSet("ADULT2")) ? record.get("ADULT2") : null;
    this.adult3 = (record.isSet("ADULT3")) ? record.get("ADULT3") : null;
    this.adult4 = (record.isSet("ADULT4")) ? record.get("ADULT4") : null;
    this.adult5 = (record.isSet("ADULT5")) ? record.get("ADULT5") : null;
    this.adult6 = (record.isSet("ADULT6")) ? record.get("ADULT6") : null;
    this.adult7 = (record.isSet("ADULT7")) ? record.get("ADULT7") : null;
    this.adult8 = (record.isSet("ADULT8")) ? record.get("ADULT8") : null;
    this.adult9 = (record.isSet("ADULT9")) ? record.get("ADULT9") : null;
    this.adult10 = (record.isSet("ADULT10")) ? record.get("ADULT10") : null;
    this.adult11 = (record.isSet("ADULT11")) ? record.get("ADULT11") : null;
    this.adult12 = (record.isSet("ADULT12")) ? record.get("ADULT12") : null;
    this.adult13 = (record.isSet("ADULT13")) ? record.get("ADULT13") : null;
    this.adult14 = (record.isSet("ADULT14")) ? record.get("ADULT14") : null;
    //this.ward = (record.isSet("Ward")) ? record.get("Ward") : null;
    //this.wardName = (record.isSet("Ward_Name")) ? record.get("Ward_Name") : null;
    //this.mo = (record.isSet("MO")) ? record.get("MO") : null;
    //this.divAddInd = (record.isSet("DivAddInd")) ? record.get("DivAddInd") : null;
    //this.gffmu = (record.isSet("GFFMU")) ? record.get("GFFMU") : null;
    this.oldSerial = (record.isSet("OldSerial")) ? record.get("OldSerial") : null;
  }

}

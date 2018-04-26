package uk.gov.ons.fwmt.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class corresponds to a single row of resources/sampledata/test.csv
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class LegacySampleEntity {
  @Id
  private String serno;
  private String tla;
  private String fp;
  private String quota;
  private String authno;
  private String employeeno;

  private String addressline1;
  private String addressline2;
  private String addressline3;
  private String addressline4;
  private String addressno;
  private String district;
  private String posttown;
  private String postcode;
  private String osgridref;

  // lfs only
  private String week;
  private String w1yr;
  private String qrtr;
  private String wavfnd;
  private String hhld;
  private String chklet;

  // kish grid - nsw only
  private String adult2;
  private String adult3;
  private String adult4;
  private String adult5;
  private String adult6;
  private String adult7;
  private String adult8;
  private String adult9;
  private String adult10;
  private String adult11;
  private String adult12;
  private String adult13;
  private String adult14;


  public boolean verify() {
    return serno != null &&
        tla != null &&
        fp != null &&
        quota != null &&
        authno != null &&
        employeeno != null &&
        addressline1 != null &&
        addressline2 != null &&
        addressline3 != null &&
        addressline4 != null &&
        addressno != null &&
        district != null &&
        posttown != null &&
        postcode != null &&
        osgridref != null;
  }
}

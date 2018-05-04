package uk.gov.ons.fwmt.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long primaryKey;
  private String legacyJobId;
  private String serno;
  private String tla;
  private String fp;
  private String quota;
  private String authNo;
  private String employeeNo;

  @Column(name="address_line_1") private String addressLine1;
  @Column(name="address_line_2") private String addressLine2;
  @Column(name="address_line_3") private String addressLine3;
  @Column(name="address_line_4") private String addressLine4;
  private String addressNo;
  private String district;
  private String postTown;
  private String postcode;
  private String osGridRef;

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
        authNo != null &&
        employeeNo != null &&
        addressLine1 != null &&
        addressLine2 != null &&
        addressLine3 != null &&
        addressLine4 != null &&
        addressNo != null &&
        district != null &&
        postTown != null &&
        postcode != null &&
        osGridRef != null;
  }
}

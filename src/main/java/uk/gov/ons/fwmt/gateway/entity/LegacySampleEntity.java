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
  private String stage;
  private String quota;
  private String authno;
  private String employeeno;
  private String addressline1;
  private String addressline2;
  private String addressline3;
  private String addressline4;
  private String district;
  private String posttown;
  private String postcode;
  private String addressno;
  private String osgridref;
  private String kishgrid;

  public boolean verify() {
    return serno != null &&
        tla != null &&
        stage != null &&
        quota != null &&
        authno != null &&
        employeeno != null &&
        addressline1 != null &&
        addressline2 != null &&
        addressline3 != null &&
        addressline4 != null &&
        district != null &&
        posttown != null &&
        postcode != null &&
        addressno != null &&
        osgridref != null;
  }
}

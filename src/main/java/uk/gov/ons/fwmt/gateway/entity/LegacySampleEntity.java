package uk.gov.ons.fwmt.gateway.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String authNo;
    private String employeeNo;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String district;
    private String postTown;
    private String postcode;
    private String addressNo;
    private String osGridRef;
    private String kishGrid;
}

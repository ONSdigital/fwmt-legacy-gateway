package uk.gov.ons.fwmt.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class LegacyJobEntity {
  @Id
  public String tmJobId;
  @Column(unique = true, nullable = true)
  public String legacyJobId;
  public String state;
  public String initalTimeStamp;
  public String sentTimeStamp;
  public String processedTimeStamp;
  public String erroredTimeStamp;
}

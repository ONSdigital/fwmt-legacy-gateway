package uk.gov.ons.fwmt.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Deprecated
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leavers")
public class LegacyLeaverEntity {
  @Id
  public String employeeNo;
  @Column(unique = true, nullable = true)
  public String authNo;
  public String forename;
  public String surname;
  public String jobTitle;
  public String email;
  public String phone;
  public String userType;
}
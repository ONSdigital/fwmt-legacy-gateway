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
@Table(name = "staff")
public class LegacyStaffEntity {
  @Id
  public String authNo;

  @Column(unique = true)
  public String employeeNo;

  @Column(nullable = false)
  public String forename;

  @Column(nullable = false)
  public String surname;

  @Column(nullable = false)
  public String jobTitle;

  @Column(nullable = false)
  public String email;

  @Column(nullable = false)
  public String phone;

  @Column(nullable = false)
  public String userType;
}
package uk.gov.ons.fwmt.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class LegacyUserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(nullable = false)
  public String authNo;

  @Column(nullable = false)
  public String tmUsername;

  public LegacyUserEntity(LegacyStaffEntity staff, String tmUsername) {
    this.setAuthNo(staff.getAuthNo());
    this.setTmUsername(tmUsername);
  }
}
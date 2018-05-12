package uk.gov.ons.fwmt.gateway.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@Table(name = "tm_users")
public class TMUserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.PRIVATE)
  public Long id;

  @Column(nullable = false)
  public String authNo;

  @Column
  public String tmUsername;

  @Column(nullable = false)
  public boolean active;

  public TMUserEntity(String authNo, String tmUsername, boolean active) {
    this.authNo = authNo;
    this.tmUsername = tmUsername;
    this.active = active;
  }
}

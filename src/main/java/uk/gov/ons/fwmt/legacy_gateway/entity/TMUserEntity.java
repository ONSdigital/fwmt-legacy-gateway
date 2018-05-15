package uk.gov.ons.fwmt.legacy_gateway.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
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

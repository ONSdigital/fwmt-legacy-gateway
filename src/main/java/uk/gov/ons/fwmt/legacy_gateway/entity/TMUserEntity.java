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

  // if this is set, it means that jobs designated for altAuthNo should be redirected to this user
  @Column
  public String altAuthNo;

  @Column
  public String tmUsername;

  @Column(nullable = false)
  public boolean active;

  @Column
  public String alternateAuthNo;

  public TMUserEntity(String authNo, String tmUsername, boolean active, String alternateAuthNo) {
    this.authNo = authNo;
    this.tmUsername = tmUsername;
    this.active = active;
    this.alternateAuthNo = alternateAuthNo;
  }
}

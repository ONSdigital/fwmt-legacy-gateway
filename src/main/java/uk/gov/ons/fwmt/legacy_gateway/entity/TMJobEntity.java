package uk.gov.ons.fwmt.legacy_gateway.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tm_jobs")
public class TMJobEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.PRIVATE)
  private Long id;

  @Column(nullable = false)
  private String tmJobId;

  @Column(nullable = false)
  private String lastAuthNo;

  public TMJobEntity(String tmJobId, String lastAuthNo) {
    this.tmJobId = tmJobId;
    this.lastAuthNo = lastAuthNo;
  }
}

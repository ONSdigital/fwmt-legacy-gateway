package uk.gov.ons.fwmt.legacy_gateway.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tm_jobs")
public class TMJobEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.PRIVATE)
  private Long id;

  @Column(nullable = false)
  private final String tmJobId;

  public TMJobEntity(String tmJobId) {
    this.tmJobId = tmJobId;
  }
}

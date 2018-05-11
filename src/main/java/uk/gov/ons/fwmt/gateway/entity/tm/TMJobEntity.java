package uk.gov.ons.fwmt.gateway.entity.tm;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Getter
@Table(name = "tm_jobs")
public class TMJobEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private final String tmJobId;

  public TMJobEntity(String tmJobId) {
    this.tmJobId = tmJobId;
  }
}

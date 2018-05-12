package uk.gov.ons.fwmt.gateway.entity.tm;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
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

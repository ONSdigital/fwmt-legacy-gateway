package uk.gov.ons.fwmt.gateway.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@Table(name = "tm_users")
public class TMUserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(nullable = false)
  public String authNo;

  @Column(nullable = false)
  public String tmUsername;
}

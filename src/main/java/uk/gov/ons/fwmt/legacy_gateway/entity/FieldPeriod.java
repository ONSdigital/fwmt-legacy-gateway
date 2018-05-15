package uk.gov.ons.fwmt.legacy_gateway.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "field_periods")
public class FieldPeriod {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.PRIVATE)
  private Long id;

  @Column(nullable = false)
  Date startDate;

  @Column(nullable = false)
  Date endDate;

  @Column(nullable = false)
  String fieldPeriod;

  public FieldPeriod(Date startDate, Date endDate, String fieldPeriod) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.fieldPeriod = fieldPeriod;
  }
}

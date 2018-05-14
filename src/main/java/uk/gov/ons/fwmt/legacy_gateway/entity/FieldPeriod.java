package uk.gov.ons.fwmt.legacy_gateway.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "field_periods")
public class FieldPeriod {
  int id;
  Date start_date;
  Date end_date;
  String field_period;
}

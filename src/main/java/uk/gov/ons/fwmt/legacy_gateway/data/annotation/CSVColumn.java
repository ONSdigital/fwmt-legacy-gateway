package uk.gov.ons.fwmt.legacy_gateway.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A simple marker for tracking when a field is mapped to a specific column of a CSV
 * Currently, this has no effect on code. It merely standardizes comments
 * <p>
 * A string is expected when one column maps to this field
 * An array of Mapping annotations is expected when many columns could map to this field
 * <p>
 * This annotation is utilized within the uk.gov.ons.fwmt.legacy_gateway.service.impl.CSVParsingServiceImpl service
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVColumn {
  String value() default "";

  Mapping[] values() default {};

  boolean mandatory() default false;

  boolean ignored() default false;

  @interface Mapping {
    String value();

    String when();
  }
}

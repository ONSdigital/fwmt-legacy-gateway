package uk.gov.ons.fwmt.legacy_gateway.data.annotation;

/**
 * A simple marker for tracking when a field is mapped to an additional property within the TM system
 * Currently, this has no effect on code. It merely standardizes comments
 *
 * A string is expected when one column maps to this field
 * An array of Mapping annotations is expected when many columns could map to this field
 */
public @interface JobAdditionalProperty {
  String value();
}

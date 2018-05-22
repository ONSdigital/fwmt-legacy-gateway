package uk.gov.ons.fwmt.legacy_gateway.data.file_ingest;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;

import java.time.LocalDateTime;

/**
 * This class describes a filename as seen by the FileIngesterService, split into constituent parts
 */
@Data
@AllArgsConstructor
public class Filename {
  private final String endpoint;
  private final LegacySampleSurveyType tla;
  private final LocalDateTime timestamp;
}

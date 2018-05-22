package uk.gov.ons.fwmt.legacy_gateway.service;

import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public interface CSVParsingService {
  Iterator<CSVParseResult<LegacySampleIngest>> parseLegacySample(Reader reader, LegacySampleSurveyType legacySampleSurveyType)
      throws IOException;

  Iterator<CSVParseResult<LegacyStaffIngest>> parseLegacyStaff(Reader reader) throws IOException;
}

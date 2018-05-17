package uk.gov.ons.fwmt.legacy_gateway.service;

import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;

import java.io.IOException;
import java.io.Reader;

public interface CSVParsingService {
  CSVParseResult parseLegacySample(Reader reader, LegacySampleSurveyType legacySampleSurveyType) throws IOException;
  CSVParseResult parseLegacyStaff(Reader reader) throws IOException;
}

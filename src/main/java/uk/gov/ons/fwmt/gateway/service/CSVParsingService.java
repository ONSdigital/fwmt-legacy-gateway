package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.internal.csv.CSVParseResult;
import uk.gov.ons.fwmt.gateway.entity.legacy.SurveyType;

import java.io.IOException;
import java.io.Reader;

@Service
public interface CSVParsingService {
  CSVParseResult parseJobs(Reader reader, SurveyType surveyType) throws IOException;

  CSVParseResult parseStaff(Reader reader) throws IOException;
}

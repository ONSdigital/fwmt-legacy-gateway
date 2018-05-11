package uk.gov.ons.fwmt.gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.internal.csv.CSVParseResult;
import uk.gov.ons.fwmt.gateway.entity.internal.csv.UnprocessedCSVRow;
import uk.gov.ons.fwmt.gateway.entity.legacy.JobIngest;
import uk.gov.ons.fwmt.gateway.entity.legacy.StaffIngest;
import uk.gov.ons.fwmt.gateway.entity.legacy.SurveyType;
import uk.gov.ons.fwmt.gateway.service.CSVParsingService;
import uk.gov.ons.fwmt.gateway.service.DBService;
import uk.gov.ons.fwmt.gateway.service.TMService;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CSVParsingServiceImpl implements CSVParsingService {
  @Autowired TMService tmService;
  @Autowired DBService dbService;

  private CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.withHeader();
  }

  private <T> CSVParseResult parse(Reader reader, Function<CSVRecord, Optional<UnprocessedCSVRow>> recordHandler)
      throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);

    Iterator<CSVRecord> iterator = parser.iterator();

    int parsedCount = 0;
    int unparsedCount = 0;
    List<UnprocessedCSVRow> unprocessedCSVRows = new ArrayList<>();

    while (iterator.hasNext()) {
      Optional<UnprocessedCSVRow> unprocessed = recordHandler.apply(iterator.next());
      if (unprocessed.isPresent()) {
        unparsedCount++;
        unprocessedCSVRows.add(unprocessed.get());
      } else {
        parsedCount++;
      }
    }

    reader.close();

    return new CSVParseResult(unprocessedCSVRows, parsedCount, unparsedCount);
  }

  @Override
  public CSVParseResult parseJobs(Reader reader, SurveyType surveyType) throws IOException {
    return parse(reader,
        (record) -> {
          try {
            JobIngest job = new JobIngest(record, surveyType);
            // TODO call TM/DB
            return Optional.empty();
          } catch (Exception e) {
            return Optional.of(new UnprocessedCSVRow());
          }
        }
    );
  }

  @Override
  public CSVParseResult parseStaff(Reader reader) throws IOException {
    return parse(reader,
        (record) -> {
          try {
            StaffIngest job = new StaffIngest(record);
            // TODO call TM/DB
            return Optional.empty();
          } catch (Exception e) {
            return Optional.of(new UnprocessedCSVRow());
          }
        }
    );
  }
}

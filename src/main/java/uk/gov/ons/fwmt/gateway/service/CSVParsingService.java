package uk.gov.ons.fwmt.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.gateway.data.csv_parser.UnprocessedCSVRow;
import uk.gov.ons.fwmt.gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.gateway.data.legacy_ingest.LegacySampleSurveyType;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
@Slf4j
public class CSVParsingService {
  LegacyStaffPublishService legacyStaffPublishService;
  LegacyJobPublishService legacyJobPublishService;

  @Autowired
  public CSVParsingService(
      LegacyStaffPublishService legacyStaffPublishService,
      LegacyJobPublishService legacyJobPublishService) {
    this.legacyStaffPublishService = legacyStaffPublishService;
    this.legacyJobPublishService = legacyJobPublishService;
  }

  private CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.withHeader();
  }

  private <T> CSVParseResult parse(Reader reader, BiFunction<Integer, CSVRecord, Optional<UnprocessedCSVRow>> recordHandler)
      throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);

    Iterator<CSVRecord> iterator = parser.iterator();

    int rowNumber = 0;
    int parsedCount = 0;
    int unparsedCount = 0;
    List<UnprocessedCSVRow> unprocessedCSVRows = new ArrayList<>();

    while (iterator.hasNext()) {
      Optional<UnprocessedCSVRow> unprocessed = recordHandler.apply(rowNumber, iterator.next());
      if (unprocessed.isPresent()) {
        unparsedCount++;
        unprocessedCSVRows.add(unprocessed.get());
      } else {
        parsedCount++;
      }
      rowNumber++;
    }

    reader.close();

    return new CSVParseResult(unprocessedCSVRows, parsedCount, unparsedCount);
  }

  /**
   * Sends each staff entity to the LegacyJobPublishService
   *
   * @param reader A reader into a CSV file
   * @return A structure denoting any issues encountered while parsing the CSV
   * @throws IOException If the file provided fails to provide content
   */
  public CSVParseResult parseLegacySample(Reader reader, LegacySampleSurveyType legacySampleSurveyType) throws IOException {
    log.info("Began parsing a sample CSV file");
    return parse(reader,
        (index, record) -> {
          try {
            LegacySampleIngest job = new LegacySampleIngest(record, legacySampleSurveyType);
            legacyJobPublishService.publishJob(job);
            return Optional.empty();
          } catch (IllegalStateException e) {
            log.error("IllegalStateException during CSV parse", e);
            return Optional.of(new UnprocessedCSVRow(index, "CSV may have been missing a header"));
          } catch (Exception e) {
            log.error("Unknown exception during CSV parse", e);
            return Optional.of(new UnprocessedCSVRow(index, e.getMessage()));
          }
        }
    );
  }

  /**
   * Reads each staff entity into a list, then hand the list off to the LegacyStaffPublishService
   *
   * @param reader A reader into a CSV file containing staff as defined by
   * @return A structure denoting any issues encountered while parsing the CSV
   * @throws IOException If the file provided fails to provide content
   */
  public CSVParseResult parseLegacyStaff(Reader reader) throws IOException {
    log.info("Began parsing a staff CSV file");
    List<LegacyStaffIngest> staff = new ArrayList<>();
    CSVParseResult result = parse(reader,
        (index, record) -> {
          try {
            LegacyStaffIngest job = new LegacyStaffIngest(record);
            staff.add(job);
            return Optional.empty();
          } catch (IllegalStateException e) {
            log.error("IllegalStateException during CSV parse", e);
            return Optional.of(new UnprocessedCSVRow(index, "CSV may have been missing a header"));
          } catch (Exception e) {
            log.error("Unknown exception during CSV parse", e);
            return Optional.of(new UnprocessedCSVRow(index, e.getMessage()));
          }
        }
    );
    legacyStaffPublishService.publishStaff(staff);
    return result;
  }
}

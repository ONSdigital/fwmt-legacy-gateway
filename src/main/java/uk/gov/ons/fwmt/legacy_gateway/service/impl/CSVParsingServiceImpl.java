package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.UnprocessedCSVRow;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.legacy_gateway.service.CSVParsingService;
import uk.gov.ons.fwmt.legacy_gateway.service.LegacyJobPublishService;
import uk.gov.ons.fwmt.legacy_gateway.service.LegacyStaffPublishService;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
@Slf4j
public class CSVParsingServiceImpl implements CSVParsingService {
  private LegacyStaffPublishService legacyStaffPublishService;
  private LegacyJobPublishService legacyJobPublishService;

  @Autowired
  public CSVParsingServiceImpl(
      LegacyStaffPublishService legacyStaffPublishService,
      LegacyJobPublishService legacyJobPublishService) {
    this.legacyStaffPublishService = legacyStaffPublishService;
    this.legacyJobPublishService = legacyJobPublishService;
  }

  private CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.withHeader();
  }

  /**
   * @param reader        A reader holding the contents of a CSV file, header included
   * @param recordHandler An anonymous function that:
   *                      Receives integer representing the number of rows and a CSV record
   *                      If an error occurs, it returns an UnprocessedCSVRow
   * @return A structure containing information on the rows that were processed and a list of errors
   * @throws IOException In the event of an error within the 'reader' parameter
   */
  private CSVParseResult parse(Reader reader, BiFunction<Integer, CSVRecord, Optional<UnprocessedCSVRow>> recordHandler)
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
   * Sends each job entity to the LegacyJobPublishService
   *
   * @param reader A reader into a CSV file
   * @return A structure containing information on the rows that were processed and a list of errors
   * @throws IOException In the event of an error within the 'reader' parameter
   */
  public CSVParseResult parseLegacySample(Reader reader, LegacySampleSurveyType legacySampleSurveyType)
      throws IOException {
    log.info("Began a legacy sample CSV parse");

    CSVParseResult result = parse(reader,
        (index, record) -> {
          try {
            log.debug("Began parsing job record " + index.toString());
            LegacySampleIngest job = new LegacySampleIngest(record, legacySampleSurveyType);
            log.debug("Parsed job record " + index.toString());
            log.debug("Sending job record " + index.toString() + " to the LegacyJobPublishService");
            legacyJobPublishService.publishJob(job);
            log.debug("Finished sending job record " + index.toString() + " to the LegacyJobPublishService");
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

    log.info("Ended a legacy sample CSV parse");

    return result;
  }

  /**
   * Reads each staff entity into a list, then hand the list off to the LegacyStaffPublishService
   *
   * @param reader A reader into a CSV file containing staff as defined by
   * @return A structure containing information on the rows that were processed and a list of errors
   * @throws IOException In the event of an error within the 'reader' parameter
   */
  public CSVParseResult parseLegacyStaff(Reader reader) throws IOException {
    log.info("Began a legacy staff CSV parse");

    List<LegacyStaffIngest> staff = new ArrayList<>();

    CSVParseResult result = parse(reader,
        (index, record) -> {
          try {
            log.debug("Began parsing record " + index.toString());
            LegacyStaffIngest job = new LegacyStaffIngest(record);
            log.debug("Parsed record " + index.toString());
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

    log.debug("Sending " + staff.size() + " staff records to the LegacyJobPublishService");
    legacyStaffPublishService.publishStaff(staff);
    log.debug("Finished sending staff records to the LegacyJobPublishService");

    log.info("Ended a legacy staff CSV parse");

    return result;
  }

}

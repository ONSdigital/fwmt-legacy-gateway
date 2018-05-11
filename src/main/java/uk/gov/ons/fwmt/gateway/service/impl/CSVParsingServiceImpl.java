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
import uk.gov.ons.fwmt.gateway.service.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Slf4j
public class CSVParsingServiceImpl implements CSVParsingService {
  LegacyStaffPublishService legacyStaffPublishService;
  LegacyJobPublishService legacyJobPublishService;

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
  @Override
  public CSVParseResult parseLegacySample(Reader reader, SurveyType surveyType) throws IOException {
    log.info("Began parsing a sample CSV file");
    return parse(reader,
        (index, record) -> {
          try {
            JobIngest job = new JobIngest(record, surveyType);
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
  @Override
  public CSVParseResult parseLegacyStaff(Reader reader) throws IOException {
    log.info("Began parsing a staff CSV file");
    List<StaffIngest> staff = new ArrayList<>();
    CSVParseResult result = parse(reader,
        (index, record) -> {
          try {
            StaffIngest job = new StaffIngest(record);
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

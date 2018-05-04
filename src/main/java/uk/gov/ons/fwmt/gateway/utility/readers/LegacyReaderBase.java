package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.representation.UnprocessedCSVRowDTO;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LegacyReaderBase<T> implements Iterator<T> {
  // CSV internals
  protected CSVParser parser;
  protected Iterator<CSVRecord> iter;

  @Getter List<UnprocessedCSVRowDTO> unprocessedCSVRows = new ArrayList<>();
  @Getter private int unprocessedCount = 0;
  @Getter private int successCount = 0;

  public LegacyReaderBase(Reader in) throws IOException {
    this.parser = CSVFormat.DEFAULT
        .withHeader(getCSVHeaders())
        .parse(in);
    this.iter = parser.iterator();
  }

  abstract String[] getCSVHeaders();

  abstract T parseRecord(CSVRecord record);

  @Override
  public boolean hasNext() {
    return iter.hasNext();
  }

  @Override
  public T next() {
    do {
      CSVRecord record = iter.next();
      T result = null;
      Exception exception = null;
      try {
        result = parseRecord(record);
      } catch (Exception e) {
        exception = e;
      }
      if (result == null) {
        unprocessedCount++;
        List<String> columns = new ArrayList<>();
        record.iterator().forEachRemaining(columns::add);
        if (exception == null) {
          unprocessedCSVRows.add(new UnprocessedCSVRowDTO(columns, (int) record.getRecordNumber(), "Unable to process"));
        } else {
          unprocessedCSVRows.add(new UnprocessedCSVRowDTO(columns, (int) record.getRecordNumber(), exception.toString()));
        }
      } else {
        successCount++;
        return result;
      }
    } while (true);
  }
}

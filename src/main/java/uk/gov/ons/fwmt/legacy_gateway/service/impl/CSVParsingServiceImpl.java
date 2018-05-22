package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.legacy_gateway.service.CSVParsingService;

import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Service
public class CSVParsingServiceImpl implements CSVParsingService {
  private static <T> T parseFromAnnotations(CSVRecord record, Class<T> tClass)
      throws InstantiationException, IllegalAccessException {
    T target = tClass.newInstance();
    PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(target);
    for (Field field : tClass.getDeclaredFields()) {
      Optional<Annotation> annotation = Arrays.stream(field.getDeclaredAnnotations())
          .filter(an -> an.annotationType() == CSVColumn.class)
          .findFirst();
      if (annotation.isPresent()) {
        CSVColumn csvColumn = (CSVColumn) annotation.get();
        String value = record.get(csvColumn.value());
        accessor.setPropertyValue(field.getName(), value);
      }
    }
    return target;
  }

  private CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.withHeader();
  }

  @Override public Iterator<CSVParseResult<LegacySampleIngest>> parseLegacySample(Reader reader,
      LegacySampleSurveyType legacySampleSurveyType) throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);
    return new CSVIterator<LegacySampleIngest>(parser) {
      @Override
      public LegacySampleIngest ingest(CSVRecord record) throws IllegalAccessException, InstantiationException {
        return parseFromAnnotations(record, LegacySampleIngest.class);
      }
    };
  }

  @Override public Iterator<CSVParseResult<LegacyStaffIngest>> parseLegacyStaff(Reader reader) throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);
    return new CSVIterator<LegacyStaffIngest>(parser) {
      @Override
      public LegacyStaffIngest ingest(CSVRecord record) throws IllegalAccessException, InstantiationException  {
        return parseFromAnnotations(record, LegacyStaffIngest.class);
      }
    };
  }

  private abstract class CSVIterator<T> implements Iterator<CSVParseResult<T>> {
    private final Iterator<CSVRecord> iter;
    private int rowNumber;

    protected CSVIterator(CSVParser parser) {
      this.rowNumber = 0;
      this.iter = parser.iterator();
    }

    abstract public T ingest(CSVRecord record) throws IllegalAccessException, InstantiationException;

    @Override public boolean hasNext() {
      return iter.hasNext();
    }

    @Override public CSVParseResult<T> next() {
      rowNumber++;
      CSVRecord record = iter.next();
      if (record == null) {
        return null;
      }
      try {
        T result = ingest(record);
        return CSVParseResult.withResult(rowNumber, result);
      } catch (Exception e) {
        return CSVParseResult.withError(rowNumber, e.toString());
      }
    }
  }
}

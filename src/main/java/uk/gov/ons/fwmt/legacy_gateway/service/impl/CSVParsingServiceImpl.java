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
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.*;
import uk.gov.ons.fwmt.legacy_gateway.service.CSVParsingService;

import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Service
public class CSVParsingServiceImpl implements CSVParsingService {
  /**
   * Read the CSVColumn annotations on the class T and set Java bean properties from the columns of a CSV record
   *
   * @param instance An instance of the class that will be mutated
   * @param record A row of a CSV file
   * @param pivot A string used to determine which field name should be used, in events where there are many options
   * @param <T> A class with fields annotated with CSVColumn
   */
  private static <T> void setFromCSVColumnAnnotations(T instance, CSVRecord record, String pivot) {
    Class<?> tClass = instance.getClass();
    PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(instance);
    for (Field field : tClass.getDeclaredFields()) {
      Optional<Annotation> annotation = Arrays.stream(field.getDeclaredAnnotations())
          .filter(an -> an.annotationType() == CSVColumn.class)
          .findFirst();
      if (annotation.isPresent()) {
        CSVColumn csvColumn = (CSVColumn) annotation.get();
        String columnName;
        if (!csvColumn.value().isEmpty()) {
          columnName = csvColumn.value();
        } else if (csvColumn.values().length > 0) {
          Optional<CSVColumn.Mapping> mapping = Arrays.stream(csvColumn.values())
              .filter(m -> m.when().equals(pivot))
              .findFirst();
          if (mapping.isPresent()) {
            columnName = mapping.get().value();
          } else {
            throw new IllegalArgumentException("Given pivot does not occur in the CSVColumn annotation");
          }
        } else {
          throw new IllegalStateException("CSVColumn lacked a 'value' or 'values' field");
        }
        if (record.isSet(columnName) || csvColumn.mandatory()) {
          accessor.setPropertyValue(field.getName(), record.get(columnName));
        }
      }
    }
  }

  /**
   * Create a unique Job ID that can be used by TotalMobile from existing fields within the CSV
   * The method varies on the type of survey
   */
  protected static String constructTmJobId(CSVRecord record, LegacySampleSurveyType surveyType) {
    switch (surveyType) {
    case GFF:
      // quota '-' addr '-' stage
      return record.get("Quota") + "-" + record.get("AddressNo") + "-" + record.get("Stage");
    case LFS:
      // quota week w1yr qrtr addr wavfnd hhld chklet
      return record.get("QUOTA") + " " + record.get("WEEK") + " " + record.get("W1YR") + " " + record.get("QRTR") + " "
          + record.get("ADDR")
          + " " + record.get("WAVFND") + " " + record.get("HHLD") + " " + record.get("CHKLET") + " - "
          + record.get("FP");
    default:
      throw new IllegalArgumentException("Invalid survey type");
    }
  }

  protected static Date convertToGFFDate(String stage) {
    int year = Integer.parseInt(stage.substring(0, 1));
    int month = Integer.parseInt(stage.substring(1, 3));
    // if we are reissuing (month above 12), we minus 20 to get a normal month
    if (month > 12) {
      month = month - 20;
      // add an extra month to the due date
      month += 1;
      // normalize the month in case we reached 13
      month = ((month - 1) % 12) + 1;
    }
    assert month > 0 && month < 13;
    Calendar cal = Calendar.getInstance();
    cal.set(2010 + year, month - 1, 1);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
    cal.set(Calendar.HOUR, 11);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.AM_PM, Calendar.PM);
    return cal.getTime();
  }

  // technically, 'stage' here is the field period 'fp'
  // TODO double check to ensure that this is correct
  protected static Date convertToLFSDate(String stage) {
    int year = Integer.parseInt(stage.substring(0, 1));
    int quarter = Integer.parseInt(stage.substring(1, 2));
    int week = stage.toLowerCase().charAt(2) - 'a' + 3;
    Calendar cal = Calendar.getInstance();
    cal.set(2010 + year, 1 + (3 * (quarter - 1)) - 1, 1);
    cal.set(Calendar.HOUR, 11);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.AM_PM, Calendar.PM);
    cal.add(Calendar.DATE, (7 * (week)) - 1);
    return cal.getTime();
  }

  private static CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.withHeader();
  }

  @Override
  public Iterator<CSVParseResult<LegacySampleIngest>> parseLegacySample(Reader reader,
      LegacySampleSurveyType legacySampleSurveyType) throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);
    return new CSVIterator<LegacySampleIngest>(parser) {
      @Override
      public LegacySampleIngest ingest(CSVRecord record) {
        LegacySampleIngest instance = new LegacySampleIngest();
        switch (legacySampleSurveyType) {
        case LFS:
          // set normal fields
          setFromCSVColumnAnnotations(instance, record, "LFS");
          // set derived due date
          instance.setDueDate(convertToLFSDate(instance.getStage()));
          // set survey type and extra data
          instance.setLegacySampleSurveyType(LegacySampleSurveyType.LFS);
          instance.setGffData(null);
          instance.setLfsData(new LegacySampleLFSDataIngest());
          setFromCSVColumnAnnotations(instance.getLfsData(), record, null);
          break;
        case GFF:
          // set normal fields
          setFromCSVColumnAnnotations(instance, record, "GFF");
          // set derived due date
          instance.setDueDate(convertToGFFDate(instance.getStage()));
          // set survey type and extra data
          instance.setLegacySampleSurveyType(LegacySampleSurveyType.GFF);
          instance.setGffData(new LegacySampleGFFDataIngest());
          instance.setLfsData(null);
          setFromCSVColumnAnnotations(instance.getGffData(), record, null);
          break;
        default:
          throw new IllegalArgumentException("Unknown survey type");
        }
        // derive the TM job id
        instance.setTmJobId(constructTmJobId(record, legacySampleSurveyType));
        // derive the coordinates, if we were given a non-null non-empty grid ref
        if (instance.getOsGridRef() != null && instance.getOsGridRef().length() > 0) {
          // TODO Confirm this is correct with new data map
          String[] osGridRefSplit = instance.getOsGridRef().split(",", 2);
          if (osGridRefSplit.length != 2) {
            throw new IllegalArgumentException("OS Grid Reference was not in the expected format");
          }
          instance.setGeoX(Float.parseFloat(osGridRefSplit[0]));
          instance.setGeoY(Float.parseFloat(osGridRefSplit[1]));
        }
        return instance;
      }
    };
  }

  @Override
  public Iterator<CSVParseResult<LegacyStaffIngest>> parseLegacyStaff(Reader reader) throws IOException {
    CSVParser parser = getCSVFormat().parse(reader);
    return new CSVIterator<LegacyStaffIngest>(parser) {
      @Override
      public LegacyStaffIngest ingest(CSVRecord record) {
        LegacyStaffIngest instance = new LegacyStaffIngest();
        setFromCSVColumnAnnotations(instance, record, null);
        return instance;
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

    abstract public T ingest(CSVRecord record);

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
        log.error("Error in CSV parser", e);
        return CSVParseResult.withError(rowNumber, e.toString());
      }
    }
  }
}

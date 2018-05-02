package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

@Slf4j
public class LegacyStaffReader {

  private static final String EMPLOYEENO = "employeeNo";
  private static final String AUTHNO = "authNo";
  private static final String FORENAME = "forename";
  private static final String SURNAME = "surname";
  private static final String JOB_TITLE = "jobTitle";
  private static final String EMAIL = "email";
  private static final String PHONE = "phone";
  private static final String USER_TYPE = "userType";
  private static final String[] NEW_USER_COLUMNS = new String[] {EMPLOYEENO, AUTHNO, FORENAME, SURNAME, JOB_TITLE,
      EMAIL, PHONE, USER_TYPE};

  CsvToBean<LegacyStaffEntity> csvToBean;
  // TODO this means is a bit old, see LegacyLFSSampleReader
  @Getter private int processedCount;

  public LegacyStaffReader(InputStream stream) {
    ColumnPositionMappingStrategy<LegacyStaffEntity> strategy = new ColumnPositionMappingStrategy<>();
    strategy.setType(LegacyStaffEntity.class);
    strategy.setColumnMapping(NEW_USER_COLUMNS);
    CsvToBeanBuilder<LegacyStaffEntity> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
    csvToBean = builder
        .withMappingStrategy(strategy)
        .withSkipLines(1)
        .build();
  }

  public Iterator<LegacyStaffEntity> iterator() {
    return new LegacyStaffIterator(csvToBean.iterator());
  }

  // We implement this pass-through iterator purely to give us a running count of the number of processed entries
  class LegacyStaffIterator implements Iterator<LegacyStaffEntity> {
    Iterator<LegacyStaffEntity> rawIterator;

    public LegacyStaffIterator(Iterator<LegacyStaffEntity> rawIterator) {
      this.rawIterator = rawIterator;
    }

    @Override public boolean hasNext() {
      return rawIterator.hasNext();
    }

    @Override public LegacyStaffEntity next() {
      processedCount++;
      return rawIterator.next();
    }
  }
}

package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.error.IllegalCSVStructureException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Slf4j
public class LegacyGFFSampleReader implements SampleReader {
  static final List<String> CSV_HEADERS;

  static final List<String> DATA_FIELDS;

  static final Map<String, String> SAMPLE_GFF_DATA_COLUMN_MAP;

  static {
    List<String> csvHeaders = new ArrayList<>();
    List<String> dataFields = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    BiConsumer<String, String> register = (csvHeader, dataField) -> {
      csvHeaders.add(csvHeader);
      dataFields.add(dataField);
      map.put(csvHeader, dataField);
    };
    register.accept("Serno", "serno");
    register.accept("TLA", "tla");
    register.accept("Stage", "stage");
    register.accept("Wave", "wave");
    register.accept("Prem1", "prem1");
    register.accept("Prem2", "prem2");
    register.accept("Prem3", "prem3");
    register.accept("Prem4", "prem4");
    register.accept("District", "district");
    register.accept("PostTown", "postTown");
    register.accept("Postcode", "postcode");
    register.accept("Quota", "quota");
    register.accept("AddressNo", "addressNo");
    register.accept("OSGridRef", "osGridRef");
    register.accept("Year", "year");
    register.accept("Month", "month");
    register.accept("LAUA", "laua");
    register.accept("LAUA_Name", "lauaName");
    register.accept("SubSample", "subSample");
    register.accept("Rand", "rand");
    register.accept("?", "kishGrid"); // TODO figure out what the name in the CSV is
    register.accept("ADULT2", "adult2");
    register.accept("ADULT3", "adult3");
    register.accept("ADULT4", "adult4");
    register.accept("ADULT5", "adult5");
    register.accept("ADULT6", "adult6");
    register.accept("ADULT7", "adult7");
    register.accept("ADULT8", "adult8");
    register.accept("ADULT9", "adult9");
    register.accept("ADULT10", "adult10");
    register.accept("ADULT11", "adult11");
    register.accept("ADULT12", "adult12");
    register.accept("ADULT13", "adult13");
    register.accept("ADULT14", "adult14");
    register.accept("Telno", "telno");
    register.accept("Issue_No", "issueNo");
    register.accept("Part", "part");
    register.accept("Auth", "auth");
    register.accept("EmployeeNo", "employeeNo");
    register.accept("Last_Updated", "lastUpdated");
    register.accept("Ward", "ward");
    register.accept("Ward_Name", "wardName");
    register.accept("MO", "mo");
    register.accept("DiffAddInd", "diffAddInd");
    register.accept("GFFMU", "gffmu");
    register.accept("OldSerial", "oldSerial");
    CSV_HEADERS = csvHeaders;
    DATA_FIELDS = dataFields;
    SAMPLE_GFF_DATA_COLUMN_MAP = map;
  }

  private CsvToBean<LegacyGFFSampleEntityRaw> csvToBean;
  @Getter private List<IllegalCSVStructureException> errorList;
  @Getter private int errorCount;
  @Getter private int successCount;

  public LegacyGFFSampleReader(InputStream stream) {
    HeaderColumnNameTranslateMappingStrategy<LegacyGFFSampleEntityRaw> strategy =
        new HeaderColumnNameTranslateMappingStrategy<>();
    strategy.setType(LegacyGFFSampleEntityRaw.class);
    strategy.setColumnMapping(SAMPLE_GFF_DATA_COLUMN_MAP);
    CsvToBeanBuilder<LegacyGFFSampleEntityRaw> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
    CsvToBeanFilter filter = new LegacyGFFSampleCSVFilter(strategy);
    csvToBean = builder
        .withMappingStrategy(strategy)
        .withFilter(filter)
        .build();
  }

  public LegacyGFFSampleIterator iterator() {
    return new LegacyGFFSampleIterator(csvToBean.iterator());
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static public class LegacyGFFSampleEntityRaw {
    String serno;
    String tla;
    String stage;
    String wave;
    String prem1;
    String prem2;
    String prem3;
    String prem4;
    String district;
    String postTown;
    String postcode;
    String quota;
    String addressNo;
    String osGridRef;
    String year;
    String month;
    String laua;
    String lauaName;
    String subSample;
    String rand;
    String kishGrid;
    String adult2;
    String adult3;
    String adult4;
    String adult5;
    String adult6;
    String adult7;
    String adult8;
    String adult9;
    String adult10;
    String adult11;
    String adult12;
    String adult13;
    String adult14;
    String telno;
    String issueNo;
    String part;
    String auth;
    String employeeNo;
    String lastUpdated;
    String ward;
    String wardName;
    String mo;
    String diffAddInd;
    String gffmu;
    String oldSerial;
  }

  class LegacyGFFSampleIterator implements Iterator<LegacySampleEntity> {
    Iterator<LegacyGFFSampleEntityRaw> rawIterator;

    LegacyGFFSampleIterator(Iterator<LegacyGFFSampleEntityRaw> rawIterator) {
      this.rawIterator = rawIterator;
    }

    @Override public boolean hasNext() {
      return rawIterator.hasNext();
    }

    @Override public LegacySampleEntity next() {
      LegacyGFFSampleEntityRaw raw = rawIterator.next();
      LegacySampleEntity entity = new LegacySampleEntity();
      entity.setSerno(raw.getSerno());
      entity.setTla(raw.getTla());
      entity.setFp(raw.getStage());
      entity.setQuota(raw.getQuota());
      entity.setAuthno(raw.getAuth());
      entity.setEmployeeno(raw.getEmployeeNo());
      entity.setAddressline1(raw.getPrem1());
      entity.setAddressline2(raw.getPrem2());
      entity.setAddressline3(raw.getPrem3());
      entity.setAddressline4(raw.getPrem4());
      entity.setDistrict(raw.getDistrict());
      entity.setPosttown(raw.getPostTown());
      entity.setPostcode(raw.getPostcode());
      entity.setAddressno(raw.getAddressNo());
      entity.setOsgridref(raw.getOsGridRef());
      entity.setAdult2(raw.adult2);
      entity.setAdult3(raw.adult3);
      entity.setAdult4(raw.adult4);
      entity.setAdult5(raw.adult5);
      entity.setAdult6(raw.adult6);
      entity.setAdult7(raw.adult7);
      entity.setAdult8(raw.adult8);
      entity.setAdult9(raw.adult9);
      entity.setAdult10(raw.adult10);
      entity.setAdult11(raw.adult11);
      entity.setAdult12(raw.adult12);
      entity.setAdult13(raw.adult13);
      entity.setAdult14(raw.adult14);
      return entity;
    }

    public LegacyGFFSampleEntityRaw nextRaw() {
      return rawIterator.next();
    }
  }

  private class LegacyGFFSampleCSVFilter implements CsvToBeanFilter {
    private final String[] requiredFields = {"Serno", "TLA", "Stage", "Quota", "Auth", "EmployeeNo", "Prem1",
        "Prem2", "Prem3", "Prem4", "District", "PostTown", "Postcode", "AddressNo", "OSGridRef"};

    private final MappingStrategy<LegacyGFFSampleEntityRaw> strategy;

    // TODO ensure that this lineCounter always begins at 1 - We must be sure that this instance is never re-used
    // It begins at 2 as the first line of the CSV is skipped
    // It should be incremented every time we begin a new line
    private int lineCounter = 1;

    LegacyGFFSampleCSVFilter(MappingStrategy<LegacyGFFSampleEntityRaw> strategy) {
      this.strategy = strategy;
    }

    private void fail(String[] strings, String reason) {
      errorCount++;
      errorList.add(new IllegalCSVStructureException(strings, lineCounter, reason));
    }

    @Override public boolean allowLine(String[] strings) {
      lineCounter++;
      for (String field : requiredFields) {
        Integer index = strategy.getColumnIndex(field);
        if (index == null) {
          // TODO add error
          fail(strings, field + " could not be found, but is required");
          return false;
        }
        String value = strings[index];
        if (value == null) {
          fail(strings, field + " was null, but is required");
          return false;
        }
        if (value.length() == 0) {
          fail(strings, field + " was empty, but is required");
          return false;
        }
      }
      successCount++;
      return true;
    }
  }
}

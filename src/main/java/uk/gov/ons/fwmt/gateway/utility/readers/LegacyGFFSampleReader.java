package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Slf4j
public class LegacyGFFSampleReader {
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

  public List<IllegalCSVStructureException> errorList;
  private CsvToBean<LegacyGFFSampleEntityRaw> csvToBean;

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
      entity.setStage(raw.getStage());
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
      entity.setKishgrid(null); // TODO fill this in from the data
      return entity;
    }

    public LegacyGFFSampleEntityRaw nextRaw() {
      return rawIterator.next();
    }
  }

  private class LegacyGFFSampleCSVFilter implements CsvToBeanFilter {
    private final MappingStrategy strategy;

    LegacyGFFSampleCSVFilter(MappingStrategy strategy) {
      this.strategy = strategy;
    }

    @Override public boolean allowLine(String[] strings) {
      String serno = strings[strategy.getColumnIndex("Serno")];
      String tla = strings[strategy.getColumnIndex("TLA")];
      String stage = strings[strategy.getColumnIndex("Stage")];
      String quota = strings[strategy.getColumnIndex("Quota")];
      String authNo = strings[strategy.getColumnIndex("Auth")];
      String employeeNo = strings[strategy.getColumnIndex("EmployeeNo")];
      String addressLine1 = strings[strategy.getColumnIndex("Prem1")];
      String addressLine2 = strings[strategy.getColumnIndex("Prem2")];
      String addressLine3 = strings[strategy.getColumnIndex("Prem3")];
      String addressLine4 = strings[strategy.getColumnIndex("Prem4")];
      String district = strings[strategy.getColumnIndex("District")];
      String postTown = strings[strategy.getColumnIndex("PostTown")];
      String postcode = strings[strategy.getColumnIndex("Postcode")];
      String addressNo = strings[strategy.getColumnIndex("AddressNo")];
      String osGridRef = strings[strategy.getColumnIndex("OSGridRef")];
//      String kishGrid = strings[strategy.getColumnIndex("")];
      Function<String, Boolean> check = (s) -> s != null && s.length() != 0;
      boolean pass = check.apply(serno) &&
          check.apply(tla) &&
          check.apply(stage) &&
          check.apply(quota) &&
          check.apply(authNo) &&
          check.apply(employeeNo) &&
          check.apply(addressLine1) &&
          check.apply(addressLine2) &&
          check.apply(addressLine3) &&
          check.apply(addressLine4) &&
          check.apply(district) &&
          check.apply(postTown) &&
          check.apply(postcode) &&
          check.apply(addressNo) &&
          check.apply(osGridRef);
      if (!pass) {
        errorList.add(new IllegalCSVStructureException(strings));
      }
      return pass;
    }
  }
}

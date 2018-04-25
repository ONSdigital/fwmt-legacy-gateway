package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;

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

  private CsvToBean<LegacyGFFSampleEntityRaw> csvToBean;

  public LegacyGFFSampleReader(InputStream stream) {
    HeaderColumnNameTranslateMappingStrategy<LegacyGFFSampleEntityRaw> strategy =
        new HeaderColumnNameTranslateMappingStrategy<>();
    strategy.setType(LegacyGFFSampleEntityRaw.class);
    strategy.setColumnMapping(SAMPLE_GFF_DATA_COLUMN_MAP);
    CsvToBeanBuilder<LegacyGFFSampleEntityRaw> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
    csvToBean = builder
        .withMappingStrategy(strategy)
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

  /// This iterator strips excess data out of the LegacyGFFSampleEntityRaw structure as it iterates
  class LegacyGFFSampleIterator implements Iterator<LegacySampleEntity> {
    Iterator<LegacyGFFSampleEntityRaw> rawIterator;

    LegacyGFFSampleIterator(Iterator<LegacyGFFSampleEntityRaw> rawIter) {
      this.rawIterator = rawIter;
    }

    @Override public boolean hasNext() {
      return rawIterator.hasNext();
    }

    LegacyGFFSampleEntityRaw nextRaw() {
      return rawIterator.next();
    }

    @Override public LegacySampleEntity next() {
      LegacyGFFSampleEntityRaw raw = rawIterator.next();
      if (raw == null) {
        return null;
      }
      LegacySampleEntity entity = new LegacySampleEntity();
      entity.setSerno(raw.getSerno());
      entity.setTla(raw.getTla());
      entity.setStage(raw.getStage());
      entity.setQuota(raw.getQuota());
      entity.setAuthNo(raw.getAuth());
      entity.setEmployeeNo(raw.getEmployeeNo());
      entity.setAddressLine1(raw.getPrem1());
      entity.setAddressLine2(raw.getPrem2());
      entity.setAddressLine3(raw.getPrem3());
      entity.setAddressLine4(raw.getPrem4());
      entity.setDistrict(raw.getDistrict());
      entity.setPostTown(raw.getPostTown());
      entity.setPostcode(raw.getPostcode());
      entity.setAddressNo(raw.getAddressNo());
      entity.setOsGridRef(raw.getOsGridRef());
      entity.setKishGrid(null);
      return entity;
    }
  }
}

package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LegacyGFFSampleReader {
  private static final Map<String, String> SAMPLE_GFF_DATA_COLUMN_MAP;

  static {
    Map<String, String> map = new HashMap<>();
    map.put("Serno", "serno");
    map.put("TLA", "tla");
    map.put("Year", "year");
    map.put("Month", "month");
    map.put("Stage", "stage");
    map.put("Wave", "wave");
    map.put("AddressLine1", "addressLine1");
    map.put("AddressLine2", "addressLine2");
    map.put("AddressLine3", "addressLine3");
    map.put("addressLine4", "addressLine4");
    map.put("District", "district");
    map.put("PostTown", "postTown");
    map.put("Postcode", "postcode");
    map.put("Quota", "quota");
    map.put("AddressNo", "addressNo");
    map.put("OSGridRef", "osGridRef");
    map.put("Issue_No", "issueNo");
    map.put("Part", "part");
    map.put("Auth", "auth");
    map.put("EmployeeNo", "employeeNo");
    map.put("Last_Updated", "lastUpdated");
    map.put("Rand", "rand");
    map.put("LAUA", "laua");
    map.put("LAUA_Name", "lauaName");
    map.put("Ward", "ward");
    map.put("Ward_Name", "wardName");
    map.put("MO", "mo");
    map.put("DiffAddInd", "diffAddInd");
    map.put("GFFMU", "gffmu");
    map.put("OldSerial", "oldSerial");
    map.put("SubSample", "subSample");
    map.put("ADULT2", "adult2");
    map.put("ADULT3", "adult3");
    map.put("ADULT4", "adult4");
    map.put("ADULT5", "adult5");
    map.put("ADULT6", "adult6");
    map.put("ADULT7", "adult7");
    map.put("ADULT8", "adult8");
    map.put("ADULT9", "adult9");
    map.put("ADULT10", "adult10");
    map.put("ADULT11", "adult11");
    map.put("ADULT12", "adult12");
    map.put("ADULT13", "adult13");
    map.put("ADULT14", "adult14");
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

  public Iterator<LegacySampleEntity> iterator() {
    return new LegacyGFFSampleIterator(csvToBean.iterator());
  }

  /// This iterator strips excess data out of the LegacyGFFSampleEntityRaw structure as it iterates
  private class LegacyGFFSampleIterator implements Iterator<LegacySampleEntity> {
    Iterator<LegacyGFFSampleEntityRaw> rawIterator;

    LegacyGFFSampleIterator(Iterator<LegacyGFFSampleEntityRaw> rawIter) {
      this.rawIterator = rawIter;
    }

    @Override public boolean hasNext() {
      return rawIterator.hasNext();
    }

    @Override public LegacySampleEntity next() {
      LegacyGFFSampleEntityRaw raw = rawIterator.next();
      if (raw == null) {
        return null;
      }
      LegacySampleEntity entity = new LegacySampleEntity();
      // TODO add fields
      return entity;
    }
  }

  @Data
  @NoArgsConstructor
  private class LegacyGFFSampleEntityRaw {
    private String serno;
    private String tla;
    private String year;
    private String month;
    private String stage;
    private String wave;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String district;
    private String postTown;
    private String postcode;
    private String quota;
    private String addressNo;
    private String osGridRef;
    private String issueNo;
    private String part;
    private String auth;
    private String employeeNo;
    private String lastUpdated;
    private String rand;
    private String laua;
    private String lauaName;
    private String ward;
    private String wardName;
    private String mo;
    private String diffAddInd;
    private String gffmu;
    private String oldSerial;
    private String subSample;
    private String adult2;
    private String adult3;
    private String adult4;
    private String adult5;
    private String adult6;
    private String adult7;
    private String adult8;
    private String adult9;
    private String adult10;
    private String adult11;
    private String adult12;
    private String adult13;
    private String adult14;
  }
}

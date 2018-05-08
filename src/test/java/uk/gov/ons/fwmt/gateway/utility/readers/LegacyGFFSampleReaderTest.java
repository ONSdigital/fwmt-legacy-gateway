package uk.gov.ons.fwmt.gateway.utility.readers;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class LegacyGFFSampleReaderTest {
  /// We test the CSV parser by creating a CSV with valid headers and a list of field names, then using comparisons to
  /// ensure that each field contains it's own name
  @Test
  public void testHeaderMapping() throws Exception {
    // create our CSV
    String header = String.join(",", LegacyGFFSampleReader.CSV_HEADERS);
    String value = String.join(",", LegacyGFFSampleReader.DATA_FIELDS);
    String input = header + "\n" + value;
    // take the first raw result from the reader
    LegacyGFFSampleReader reader = new LegacyGFFSampleReader(new ByteArrayInputStream(input.getBytes()));
    LegacyGFFSampleReader.LegacyGFFSampleEntityRaw output = reader.iterator().nextRaw();
    // try all of the fields to ensure that they were filled with the field name
    Class<LegacyGFFSampleReader.LegacyGFFSampleEntityRaw> entityRawClass = LegacyGFFSampleReader.LegacyGFFSampleEntityRaw.class;
    Field[] fields = entityRawClass.getDeclaredFields();
    for (Field field : fields) {
      Assert.assertEquals(field.getName(), field.get(output));
    }
  }
}

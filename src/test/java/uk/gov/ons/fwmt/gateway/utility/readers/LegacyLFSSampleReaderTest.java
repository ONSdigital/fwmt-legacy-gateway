//package uk.gov.ons.fwmt.gateway.utility.readers;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.ByteArrayInputStream;
//import java.lang.reflect.Field;
//
//public class LegacyLFSSampleReaderTest {
//  /// We test the CSV parser by creating a CSV with valid headers and a list of field names, then using comparisons to
//  /// ensure that each field contains it's own name
//  @Test
//  public void testHeaderMapping() throws Exception {
//    // create our CSV
//    String header = String.join(",", LegacyLFSSampleReader.CSV_HEADERS);
//    String value = String.join(",", LegacyLFSSampleReader.DATA_FIELDS);
//    String input = header + "\n" + value;
//    // take the first raw result from the reader
//    LegacyLFSSampleReader reader = new LegacyLFSSampleReader(new ByteArrayInputStream(input.getBytes()));
//    LegacyLFSSampleReader.LegacyLFSSampleEntityRaw output = reader.iterator().nextRaw();
//    // try all of the fields to ensure that they were filled with the field name
//    Class<LegacyLFSSampleReader.LegacyLFSSampleEntityRaw> entityRawClass = LegacyLFSSampleReader.LegacyLFSSampleEntityRaw.class;
//    Field[] fields = entityRawClass.getDeclaredFields();
//    for (Field field : fields) {
//      Assert.assertEquals(field.getName(), field.get(output));
//    }
//  }
//}

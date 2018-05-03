package uk.gov.ons.fwmt.gateway.utility.readers;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.util.Iterator;
import java.util.List;

public interface SampleReader {
  Iterator<LegacySampleEntity> iterator();
  List<UnprocessedCSVRow> getUnprocessedCSVRows();
  int getUnprocessedCount();
  int getSuccessCount();
}

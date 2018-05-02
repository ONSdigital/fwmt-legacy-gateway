package uk.gov.ons.fwmt.gateway.utility.readers;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.error.IllegalCSVStructureException;

import java.util.Iterator;
import java.util.List;

public interface SampleReader {
  Iterator<LegacySampleEntity> iterator();
  List<IllegalCSVStructureException> getErrorList();
  int getErrorCount();
  int getSuccessCount();
}

package uk.gov.ons.fwmt.legacy_gateway.data.file_ingest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Reader;

@Data
@AllArgsConstructor
public class FileIngest {
  private final Filename filename;
  private final Reader reader;
}
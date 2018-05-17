package uk.gov.ons.fwmt.legacy_gateway.service;

import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.SampleSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.StaffSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;

import java.io.IOException;

public interface FileIngestService {
  SampleSummaryDTO ingestSampleFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException;
  StaffSummaryDTO ingestStaffFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException;
}

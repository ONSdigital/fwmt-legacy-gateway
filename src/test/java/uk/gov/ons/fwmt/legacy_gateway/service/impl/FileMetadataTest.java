package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;

import java.io.IOException;

public class FileMetadataTest {
  private static String VALID_FILE_NAME = "sample_GFF_2018-04-24T19:09:54Z.csv";

  private FileIngestServiceImpl fileIngestServiceImpl;

  public FileMetadataTest() {
    CSVParsingServiceImpl csvParsingServiceImplMock = Mockito.mock(CSVParsingServiceImpl.class);
    this.fileIngestServiceImpl = new FileIngestServiceImpl(csvParsingServiceImplMock);
  }

  @Test
  public void testValidMetadata() throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    MockMultipartFile mockMultipartFile = new MockMultipartFile(VALID_FILE_NAME, VALID_FILE_NAME, "text/csv",
        new byte[] {});
    fileIngestServiceImpl.verifyCSVFileMetadata(mockMultipartFile);
  }

  @Test(expected = MediaTypeNotSupportedException.class)
  public void testInvalidMetadata() throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    MockMultipartFile mockMultipartFile = new MockMultipartFile(VALID_FILE_NAME, VALID_FILE_NAME, "form-data/multipart",
        new byte[] {});
    fileIngestServiceImpl.verifyCSVFileMetadata(mockMultipartFile);
  }
}

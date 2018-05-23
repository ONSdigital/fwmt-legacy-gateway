package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.UnprocessedCSVRow;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.SampleSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.StaffSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.data.file_ingest.FileIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class LegacyServiceImpl implements LegacyService {
  private FileIngestService fileIngestService;
  private CSVParsingService csvParsingService;
  private TMJobConverterService tmJobConverterService;
  private LegacyStaffPublishService legacyStaffPublishService;
  private TMService tmService;
  private TMUserRepo tmUserRepo;
  private TMJobRepo tmJobRepo;

  @Autowired
  public LegacyServiceImpl(
      FileIngestService fileIngestService,
      CSVParsingService csvParsingService,
      TMJobConverterService tmJobConverterService,
      LegacyStaffPublishService legacyStaffPublishService,
      TMService tmService,
      TMUserRepo tmUserRepo,
      TMJobRepo tmJobRepo
  ) {
    this.fileIngestService = fileIngestService;
    this.csvParsingService = csvParsingService;
    this.tmJobConverterService = tmJobConverterService;
    this.legacyStaffPublishService = legacyStaffPublishService;
    this.tmService = tmService;
    this.tmUserRepo = tmUserRepo;
    this.tmJobRepo = tmJobRepo;
  }

  @Override public SampleSummaryDTO processSampleFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    FileIngest fileIngest = fileIngestService.ingestSampleFile(file);
    Iterator<CSVParseResult<LegacySampleIngest>> csvRowIterator = csvParsingService
        .parseLegacySample(fileIngest.getReader(), fileIngest.getFilename().getTla());

    int parsed = 0;
    List<UnprocessedCSVRow> unprocessed = new ArrayList<>();

    while (csvRowIterator.hasNext()) {
      CSVParseResult<LegacySampleIngest> result = csvRowIterator.next();
      if (result.isResult()) {
        LegacySampleIngest ingest = result.getResult();
        try {
          log.info("Auth: " + ingest.getAuth());
          if (tmUserRepo.existsByAuthNo(ingest.getAuth())) {
            String username = tmUserRepo.findByAuthNo(ingest.getAuth()).getTmUsername();
            // TODO detect reallocations
            if (true) {
              CreateJobRequest request = tmJobConverterService.createNewJob(ingest, username);
              tmService.send(request);
              log.info("TM Send");
//              tmJobRepo.save(new TMJobEntity());
            } else {
              CreateJobRequest request = tmJobConverterService.createNewJob(ingest, username);
              tmService.send(request);
              log.info("TM Else");
//              tmJobRepo.save(new TMJobEntity());
            }

          }
          parsed++;
        } catch (Exception e) {
          // TODO handle errors
          e.printStackTrace();
        }
      } else {
        unprocessed.add(new UnprocessedCSVRow(result.getRow(), "unknown"));
      }

    }

    // construct reply
    SampleSummaryDTO summary = new SampleSummaryDTO(file.getOriginalFilename(), parsed, unprocessed);

    return summary;
  }

  @Override public StaffSummaryDTO processStaffFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    FileIngest fileIngest = fileIngestService.ingestStaffFile(file);
    Iterator<CSVParseResult<LegacyStaffIngest>> csvRowIterator = csvParsingService
        .parseLegacyStaff(fileIngest.getReader());

    int parsed = 0;

    // parse csv
    // lines are recorded in the database
    // TODO determine where the 'result' of the staff delta goes
//    CSVParseFinalResult result = csvParsingService.parseLegacyStaff(new InputStreamReader(file.getInputStream()));

    // construct reply
    StaffSummaryDTO summary = new StaffSummaryDTO(file.getOriginalFilename(), parsed);

    log.info("Ended a staff file ingest");

    return summary;
  }
}

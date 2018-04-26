/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyLeaversReader;
import uk.gov.ons.fwmt.gateway.utility.HTTPResponse;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyLFSSampleReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyStaffReader;

/**
 * Class for file upload controller
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@RestController
public class LegacyEndpointRESTController {

    private final IngesterService ingesterService;

    @Autowired
    public LegacyEndpointRESTController(IngesterService ingesterService) {
        this.ingesterService = ingesterService;
    }
    
    public HTTPResponse confirmFile(MultipartFile file, String endpoint, HTTPResponse httpResponse) {
        // confirm data is in correct format
        if (!confirmFilename(file, endpoint)) {
            httpResponse.setError("BAD_REQUEST");
            httpResponse.setMessage("The file name specified in the request does not match the file name format expected by the endpoint");
            httpResponse.setPath("/"+endpoint);
            httpResponse.setTimestamp(new Date());
            return httpResponse;
        } else if (!confirmFiletype(file)) {
            httpResponse.setError("UNSUPPORTED_MEDIA_TYPE");
            httpResponse.setMessage("Recieved non-CSV file");
            httpResponse.setPath("/"+endpoint);
            httpResponse.setTimestamp(new Date());
            return httpResponse;
        } else {
            return httpResponse;
        }
        
    }

    public boolean confirmFilename(MultipartFile file, String endpoint) {
        String filename = file.getOriginalFilename();
        String[] filenameSplit = filename.split("\\.");
        String[] nameSplit = filenameSplit[0].split("_");
        String fileEndpoint = nameSplit[0];
        String surveyTla = nameSplit[1];
        String timestamp = nameSplit[2];
        if(fileEndpoint.equals(endpoint)) {
            if(surveyTla.length()==3) {
                //change this
                return !timestamp.isEmpty();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean confirmFiletype(MultipartFile file) {
        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();
        if(contentType.equals("text/csv")) {
            String[] filenameSplit = filename.split("\\.");
            return filenameSplit[filenameSplit.length-1].equals("csv");
        }else {
            return false;
        }
    }

    @RequestMapping(value = "/sample", method = RequestMethod.POST, produces = "application/json")
    public HTTPResponse sampleREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse = confirmFile(file, "sample", httpResponse);
        
        // add data to reception table
        LegacyLFSSampleReader legacyLFSSampleReader = new LegacyLFSSampleReader(file.getInputStream());
        Iterator<LegacySampleEntity> iterator = legacyLFSSampleReader.iterator();
        ingesterService.ingestLegacySample(iterator);
        httpResponse.setFilename(file.getOriginalFilename());
        // change this to number of rows in the CSV
        httpResponse.setRows(5000);

        return httpResponse;
    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST, produces = "application/json")
    public HTTPResponse staffREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse = confirmFile(file, "staff", httpResponse);
        
        // add data to reception table
        LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
        Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();
        ingesterService.ingestLegacyStaff(iterator);
        httpResponse.setFilename(file.getOriginalFilename());
        // change this to number of rows in the CSV
        httpResponse.setRows(5000);

        return httpResponse;
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    public HTTPResponse infoREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {
        
        return new HTTPResponse();
    }

    
    // Not Currently Requried
//    @RequestMapping(value = "/legacy/leavers", method = RequestMethod.POST)
//    public ResponseEntity<?> leaversREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
//            throws IOException {
//
//        // confirm data is in correct format
//        if (!confirmFileType(file)) {
//            return new ResponseEntity<>("Invalid file format",HttpStatus.I_AM_A_TEAPOT);
//        }
//        
//        // add data to reception table
//        LegacyLeaversReader legacyLeaversReader = new LegacyLeaversReader(file.getInputStream());
//        Iterator<LegacyLeaverEntity> iterator = legacyLeaversReader.iterator();
//        ingesterService.ingestLegacyLeavers(iterator);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
    
    
}

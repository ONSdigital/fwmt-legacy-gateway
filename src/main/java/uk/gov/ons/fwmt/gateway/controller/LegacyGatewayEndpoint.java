/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import java.io.IOException;
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
import uk.gov.ons.fwmt.gateway.utility.readers.LegacySampleReader;
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
public class LegacyGatewayEndpoint {

    private final IngesterService ingesterService;

    @Autowired
    public LegacyGatewayEndpoint(IngesterService ingesterService) {
        this.ingesterService = ingesterService;
    }

    public boolean confirm(MultipartFile file) {
        return true;
    }

    @RequestMapping(value = "/samples", method = RequestMethod.POST)
    public ResponseEntity<?> sampleREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        // confirm data is in correct format
        if (!confirm(file)) {
            return new ResponseEntity<>("Invalid file format",HttpStatus.BAD_REQUEST);
        }
        
        // add data to reception table
        LegacySampleReader legacySampleReader = new LegacySampleReader(file.getInputStream());
        Iterator<LegacySampleEntity> iterator = legacySampleReader.iterator();
        ingesterService.ingestLegacySample(iterator);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.POST)
    public ResponseEntity<?> staffREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        // confirm data is in correct format
        if (!confirm(file)) {
            return new ResponseEntity<>("Invalid file format",HttpStatus.BAD_REQUEST);
        }
        
        // add data to reception table
        LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
        Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();
        ingesterService.ingestLegacyStaff(iterator);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/leavers", method = RequestMethod.POST)
    public ResponseEntity<?> leaversREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        // confirm data is in correct format
        if (!confirm(file)) {
            return new ResponseEntity<>("Invalid file format",HttpStatus.BAD_REQUEST);
        }
        
        // add data to reception table
        LegacyLeaversReader legacyLeaversReader = new LegacyLeaversReader(file.getInputStream());
        Iterator<LegacyLeaverEntity> iterator = legacyLeaversReader.iterator();
        ingesterService.ingestLegacyLeavers(iterator);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}

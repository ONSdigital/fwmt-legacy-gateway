/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.service.IngesterService;

/**
 * Class for file upload controller
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@Controller
public class TotalmobileEndpointRESTController {
    
    private final IngesterService ingesterService;

    @Autowired
    public TotalmobileEndpointRESTController(IngesterService ingesterService) {
        this.ingesterService = ingesterService;
    }

    @PostMapping("/totalmobile/createvisitrequest")
    public MultipartFile createVisitRequestREST(@RequestParam("fileAllocation") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        // update job table state from sent to processed of the specified job
        
        return file;
    }
}

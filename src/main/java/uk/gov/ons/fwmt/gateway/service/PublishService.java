package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;

@Service
public interface PublishService {
    void publishNewJobsReallocationsAndReissues();
    void publishUpdateUsers();
}

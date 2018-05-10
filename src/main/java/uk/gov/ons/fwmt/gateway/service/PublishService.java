package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

import java.util.Iterator;

@Service
public interface PublishService {
    void publishNewJobsReallocationsAndReissues(
        Iterator<LegacySampleEntity> iter);
    void publishUpdateUsers();
}

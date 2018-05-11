package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

import java.util.Iterator;

@Deprecated
@Service
public interface IngesterService {
    void ingestLegacySample(Iterator<LegacySampleEntity> iter);
    void ingestLegacyStaff(Iterator<LegacyStaffEntity> iter);
}

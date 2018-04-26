package uk.gov.ons.fwmt.gateway.service;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

@Service
public interface IngesterService {
    int ingestLegacySample(Iterator<LegacySampleEntity> iter);
    int ingestLegacyStaff(Iterator<LegacyStaffEntity> iter);
}

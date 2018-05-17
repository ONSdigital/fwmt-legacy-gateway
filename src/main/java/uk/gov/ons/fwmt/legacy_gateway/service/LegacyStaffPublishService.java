package uk.gov.ons.fwmt.legacy_gateway.service;

import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;

import java.util.List;

public interface LegacyStaffPublishService {
  void publishStaff(List<LegacyStaffIngest> staff);
}

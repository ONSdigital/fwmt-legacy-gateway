package uk.gov.ons.fwmt.gateway.service;

import uk.gov.ons.fwmt.gateway.entity.legacy.StaffIngest;

import java.util.List;

public interface LegacyStaffPublishService {
  void publishStaff(List<StaffIngest> staff);
}

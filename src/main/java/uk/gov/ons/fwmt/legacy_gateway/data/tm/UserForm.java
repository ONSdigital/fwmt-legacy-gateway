package uk.gov.ons.fwmt.legacy_gateway.data.tm;

import lombok.Data;

@Data
public class UserForm {
  private String userName;
  private String password;
  private String forename;
  private String surname;
  private String email;
  private String telNo;
  private String jobTitle;
  private Boolean isApproved;
  private Boolean passwordNeverExpires;
}

package uk.gov.ons.fwmt.legacy_gateway.utility.csvconverter;

public class LegacyCreateJobRequestFactory {

//  /**
//   * Build the initial outlive of a CreateJobRequest
//   */
//  private static CreateJobRequest buildRequest() {
//    CreateJobRequest request = new CreateJobRequest();
//    JobType job = new JobType();
//    request.setJob(job);
//    job.setLocation(new LocationType());
//    job.setIdentity(new JobIdentityType());
//    job.setMandatoryResource(new ResourceIdentityType());
//    job.getLocation().setAddressDetail(new AddressDetailType());
//    job.getLocation().getAddressDetail().setLines(new AddressDetailType.Lines());
//    job.setContact(new ContactInfoType());
//    job.setAttributes(new NameValueAttributeCollectionType());
//    job.setAllocatedTo(new ResourceIdentityType());
//    job.setSkills(new SkillCollectionType());
//
//    return request;
//  }
//
//  /**
//   * Fill the initial outline of a CreateJobRequest with the data from some sample
//   * data
//   *
//   * @throws DatatypeConfigurationException
//   */
//  private static CreateJobRequest buildRequestFromSampleData(LegacySampleEntity entry, String username) {
//    CreateJobRequest request = buildRequest();
//
//    // identity
//    request.getJob().getIdentity().setReference(composeReference(entry));
//
//    // location
//    LocationType location = request.getJob().getLocation();
//    List<String> addressLines = location.getAddressDetail().getLines().getAddressLine();
//    addressLines.add(entry.getAddressLine1());
//    addressLines.add(entry.getAddressLine2());
//    addressLines.add(entry.getAddressLine3());
//    addressLines.add(entry.getAddressLine4());
//    location.getAddressDetail().setPostCode(entry.getPostcode());
//    location.setReference(entry.getSerno());
//
//    // TODO Confirm this is correct with new data map
//    // Float geoX = Float.parseFloat(entry.getOsgridref().substring(0, 6));
//    // Float geoY = Float.parseFloat(entry.getOsgridref().substring(7));
//    // request.getJob().getLocation().getAddressDetail().setGeoX(factory.createAddressTypeGeoX(geoX));
//    // request.getJob().getLocation().getAddressDetail().setGeoY(factory.createAddressTypeGeoX(geoY));
//
//    request.getJob().getContact().setName(entry.getPostcode());
//
//    // skills
//    request.getJob().getSkills().getSkill().add("Survey");
//
//    Date dueDate = fieldPeriodToDates(entry.getFp(), entry.getTla());
//
//    GregorianCalendar dueDateCalendar = new GregorianCalendar();
//    dueDateCalendar.setTime(dueDate);
//    XMLGregorianCalendar dueDateGC;
//    try {
//      dueDateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dueDateCalendar);
//      request.getJob().setDueDate(dueDateGC);
//    } catch (DatatypeConfigurationException e) {
//      // TODO fix the error handling here
//      e.printStackTrace();
//    }
//
//    // other required values
//    request.getJob().setVisitComplete(false);
//    request.getJob().setDescription(entry.getTla());
//    request.getJob().setDuration(1);
//    request.getJob().setWorkType("SS");
//    request.getJob().setDispatched(false);
//    request.getJob().setAppointmentPending(false);
//    request.getJob().setEmergency(false);
//
//    // additional properties
//    AdditionalPropertyCollectionType additionalPropertyCollectionType = new AdditionalPropertyCollectionType();
//    AdditionalPropertyType additionalProperty = new AdditionalPropertyType();
//    additionalProperty.setName("TLA");
//    additionalProperty.setValue(entry.getTla());
//    additionalPropertyCollectionType.getAdditionalProperty().add(additionalProperty);
//    request.getJob().setAdditionalProperties(additionalPropertyCollectionType);
//
//    // interviewer allocation
//    ResourceIdentityType resourceIdentityType = new ResourceIdentityType();
//    resourceIdentityType.setUsername(username);
//    request.getJob().setAllocatedTo(resourceIdentityType);
//
//    return request;
//  }
//
//  public static String composeReference(LegacySampleEntity entry) {
//    String reference;
//    if (entry.getTla().equals("LFS")) {
//      reference = entry.getQuota() + entry.getWeek() + entry.getW1yr() + entry.getQrtr() + entry.getAddressNo()
//          + entry.getWavfnd() + entry.getHhld() + entry.getChklet();
//    } else {
//      reference = entry.getQuota() + "-" + entry.getAddressNo() + "-" + entry.getFp();
//    }
//    return reference;
//  }
//
//  public static Date fieldPeriodToDates(String fp, String tla) {
//    Date date;
//    if ("LFS".equals(tla)) {
//      date = convertToLFSDate(fp);
//    } else {
//      date = convertToGFFDate(fp);
//    }
//    return date;
//  }
//
//  public static Date convertToGFFDate(String fp) {
//    Date date;
//    int year = Integer.parseInt(fp.substring(0, 1));
//    int month = Integer.parseInt(fp.substring(1, 3));
//    if (month > 12) {
//      month = month - 20;
//    }
//    Calendar cal = Calendar.getInstance();
//    cal.set(2010 + year, month - 1, 1);
//    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
//    cal.set(Calendar.HOUR, 11);
//    cal.set(Calendar.MINUTE, 59);
//    cal.set(Calendar.SECOND, 59);
//    cal.set(Calendar.AM_PM, Calendar.PM);
//    date = cal.getTime();
//    return date;
//  }
//
//  public static Date convertToLFSDate(String fp) {
//    Date date;
//    int year = Integer.parseInt(fp.substring(0, 1));
//    int quarter = Integer.parseInt(fp.substring(1, 2));
//    int week = fp.toLowerCase().charAt(2) - 'a' + 3;
//    Calendar cal = Calendar.getInstance();
//    cal.set(2010 + year, 1 + (3 * (quarter - 1)) - 1, 1);
//    cal.set(Calendar.HOUR, 11);
//    cal.set(Calendar.MINUTE, 59);
//    cal.set(Calendar.SECOND, 59);
//    cal.set(Calendar.AM_PM, Calendar.PM);
//    cal.add(Calendar.DATE, (7 * (week)) - 1);
//    date = cal.getTime();
//    return date;
//  }
//
//  public static CreateJobRequest convert(LegacySampleEntity sample, String username) {
//    return buildRequestFromSampleData(sample, username);
//  }
}

package uk.gov.ons.fwmt.gateway.entity.legacy;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class StaffIngest {
    // taken from the 'TransmissionDate' field
    // mandatory
    private final String timestamp;

    // taken from the 'Auth' field
    // mandatory
    private final String auth;

    // taken from the 'sex' field
    // ignored
    //private final String sex;

    // taken from the 'status' field
    // mandatory
    private final String title;

    // taken from the 'firstname' field
    // mandatory
    private final String firstname;

    // taken from the 'name' field
    // mandatory
    private final String surname;

    // taken from the 'add1' field
    // mandatory
    private final String addressLine1;

    // taken from the 'add2' field
    private final String addressLine2;

    // taken from the 'add3' field
    private final String addressLine3;

    // taken from the 'add4' field
    private final String addressLine4;

    // taken from the 'postcode' field
    private final String postcode;

    // taken from the 'tel' field
    private final String telNo;

    // taken from the 'tel2' field
    private final String telNo2;

    // taken from the 'mob_tel' field
    private final String telNoMobile;

    // taken from the 'emerg_tel' field
    private final String telNoEmerg;

    // taken from the 'email_add' field
    // mandatory
    private final String email;

    // taken from the 'GOR' field
    // ignored
    //private final String gor;

    // taken from the 'country' field
    private final String country;

    // taken from the 'grid_ref' field
    private final String gridRef;

    // taken from the 'birthdate' field
    // ignored
    //private final String birthdate;

    // taken from the 'inactiveno' field
    // ignored
    //private final String inactiveNo;

    // taken from the 'EmployeeNo' field
    // mandatory
    private final String employeeNo;

    // taken from the 'Easting' field
    // duplicates gridRef
    private final String easting;

    // taken from the 'Northing' field
    // duplicates gridRef
    private final String northing;

    // taken from the 'fieldmanager' field
    private final String fieldManager;

    // taken from the 'Manager_EmployeeNo' field
    // ignored
    //private final String managerEmployeeNo;

    // taken from the 'Manager_Auth' field
    // ignored
    //private final String managerAuth;

    // taken from the 'Manager_Firstname' field
    private final String managerFirstname;

    // taken from the 'Manager_Surname' field
    private final String managerSurname;

    // taken from the 'Region' field
    // ignored
    //private final String region;

    // taken from the 'regionName' field
    // ignored
    //private final String regionName;

    // taken from the 'RM_EmployeeNo' field
    // ignored
    //private final String rmEmployeeNo;

    // taken from the 'RM_Firstname' field
    // ignored
    //private final String rmFirstname;

    // taken from the 'RM_Surname' field
    // ignored
    //private final String rmSurname;

    public StaffIngest(CSVRecord record) {
        this.timestamp = record.get("TransmissionDate");
        this.auth = record.get("Auth");
        //this.sex = (record.isSet("sex")) ? record.get("sex") : null;
        this.title = record.get("status");
        this.firstname = record.get("firstname");
        this.surname = record.get("name");
        this.addressLine1 = record.get("add1");
        this.addressLine2 = (record.isSet("add2")) ? record.get("add2") : null;
        this.addressLine3 = (record.isSet("add3")) ? record.get("add3") : null;
        this.addressLine4 = (record.isSet("add4")) ? record.get("add4") : null;
        this.postcode = (record.isSet("postcode")) ? record.get("postcode") : null;
        this.telNo = (record.isSet("tel")) ? record.get("tel") : null;
        this.telNo2 = (record.isSet("tel2")) ? record.get("tel2") : null;
        this.telNoMobile = (record.isSet("mob_tel")) ? record.get("mob_tel") : null;
        this.telNoEmerg = (record.isSet("emerg_tel")) ? record.get("emerg_tel") : null;
        this.email = record.get("email_add");
        //this.gor = (record.isSet("GOR")) ? record.get("GOR") : null;
        this.country = (record.isSet("country")) ? record.get("country") : null;
        this.gridRef = (record.isSet("grid_ref")) ? record.get("grid_ref") : null;
        //this.birthdate = (record.isSet("birthdate")) ? record.get("birthdate") : null;
        //this.inactiveNo = (record.isSet("inactiveno")) ? record.get("inactiveno") : null;
        this.employeeNo = record.get("EmployeeNo");
        this.easting = (record.isSet("Easting")) ? record.get("Easting") : null;
        this.northing = (record.isSet("Northing")) ? record.get("Northing") : null;
        this.fieldManager = (record.isSet("fieldmanager")) ? record.get("fieldmanager") : null;
        //this.managerEmployeeNo = (record.isSet("Manager_EmployeeNo")) ? record.get("Manager_EmployeeNo") : null;
        //this.managerAuth = (record.isSet("Manager_Auth")) ? record.get("Manager_Auth") : null;
        this.managerFirstname = (record.isSet("Manager_Firstname")) ? record.get("Manager_Firstname") : null;
        this.managerSurname = (record.isSet("Manager_Surname")) ? record.get("Manager_Surname") : null;
        //this.region = (record.isSet("Region")) ? record.get("Region") : null;
        //this.regionName = (record.isSet("regionName")) ? record.get("regionName") : null;
        //this.rmEmployeeNo = (record.isSet("RM_EmployeeNo")) ? record.get("RM_EmployeeNo") : null;
        //this.rmFirstname = (record.isSet("RM_Firstname")) ? record.get("RM_Firstname") : null;
        //this.rmSurname = (record.isSet("RM_Surname")) ? record.get("RM_Surname") : null;
    }
}

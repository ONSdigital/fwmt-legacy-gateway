package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVColumn;

@Data
public class LegacyStaffIngest {
    // TODO is this 'Transmission_Date'?
    @CSVColumn(value = "TransmissionDate", mandatory = true)
    private final String timestamp;

    // TODO is this 'intnum'?
    @CSVColumn(value = "Auth", mandatory = true)
    private final String auth;

    //@CSVColumn(value = "sex", ignored = true)
    //private final String sex;

    @CSVColumn(value = "status", mandatory = true)
    private final String title;

    @CSVColumn(value = "firstname", mandatory = true)
    private final String firstname;

    @CSVColumn(value = "name", mandatory = true)
    private final String surname;

    @CSVColumn(value = "add1", mandatory = true)
    private final String addressLine1;

    @CSVColumn("add2")
    private final String addressLine2;

    @CSVColumn("add3")
    private final String addressLine3;

    @CSVColumn("add4")
    private final String addressLine4;

    @CSVColumn("postcode")
    private final String postcode;

    @CSVColumn("tel")
    private final String telNo;

    @CSVColumn("tel2")
    private final String telNo2;

    @CSVColumn("mob_tel")
    private final String telNoMobile;

    @CSVColumn("emerg_tel")
    private final String telNoEmerg;

    @CSVColumn(value = "email_add", mandatory = true)
    private final String email;

    //@CSVColumn(value = "GOR", ignored = true)
    //private final String gor;

    @CSVColumn("country")
    private final String country;

    @CSVColumn("grid_ref")
    private final String gridRef;

    //@CSVColumn(value = "birthdate", ignored = true)
    //private final String birthdate;

    //@CSVColumn(value = "inactiveno", ignored = true)
    //private final String inactiveNo;

    @CSVColumn(value = "EmployeeNo", mandatory = true)
    private final String employeeNo;

    // duplicates gridRef
    @CSVColumn("Easting")
    private final String easting;

    // duplicates gridRef
    @CSVColumn("Northing")
    private final String northing;

    @CSVColumn("fieldmanager")
    private final String fieldManager;

    //@CSVColumn(value = "Manager_EmployeeNo", ignored = true)
    //private final String managerEmployeeNo;

    //@CSVColumn(value = "Manager_Auth", ignored = true)
    //private final String managerAuth;

    @CSVColumn("Manager_Firstname")
    private final String managerFirstname;

    @CSVColumn("Manager_Surname")
    private final String managerSurname;

    //@CSVColumn(value = "Region", ignored = true)
    //private final String region;

    //@CSVColumn(value = "regionName", ignored = true)
    //private final String regionName;

    //@CSVColumn(value = "RM_EmployeeNo", ignored = true)
    //private final String rmEmployeeNo;

    //@CSVColumn(value = "RM_Firstname", ignored = true)
    //private final String rmFirstname;

    //@CSVColumn(value = "RM_Surname", ignored = true)
    //private final String rmSurname;

    public LegacyStaffIngest(CSVRecord record) {
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

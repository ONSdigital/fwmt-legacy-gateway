package uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.legacy_gateway.data.annotation.CSVColumn;

@Data
@NoArgsConstructor
public class LegacyStaffIngest {
    // TODO should this be 'TransmissionDate'?
    @CSVColumn(value = "Transmission_Date", mandatory = true)
    private String timestamp;

    // TODO should this be 'Auth'?
    @CSVColumn(value = "intnum", mandatory = true)
    private String auth;

    //@CSVColumn(value = "sex", ignored = true)
    //private String sex;

    // TODO should this be 'status'?
    @CSVColumn(value = "Title", mandatory = true)
    private String title;

    @CSVColumn(value = "firstname", mandatory = true)
    private String firstname;

    // TODO should this be 'name'?
    @CSVColumn(value = "Surname", mandatory = true)
    private String surname;

    @CSVColumn(value = "add1", mandatory = true)
    private String addressLine1;

    @CSVColumn("add2")
    private String addressLine2;

    @CSVColumn("add3")
    private String addressLine3;

    @CSVColumn("add4")
    private String addressLine4;

    @CSVColumn("postcode")
    private String postcode;

    @CSVColumn("tel")
    private String telNo;

    @CSVColumn("tel2")
    private String telNo2;

    @CSVColumn("mob_tel")
    private String telNoMobile;

    @CSVColumn("emerg_tel")
    private String telNoEmerg;

    @CSVColumn(value = "email_add", mandatory = true)
    private String email;

    //@CSVColumn(value = "GOR", ignored = true)
    //private String gor;

    @CSVColumn("country")
    private String country;

    @CSVColumn("grid_ref")
    private String gridRef;

    //@CSVColumn(value = "birthdate", ignored = true)
    //private String birthdate;

    //@CSVColumn(value = "inactiveno", ignored = true)
    //private String inactiveNo;

    @CSVColumn(value = "EmployeeNo", mandatory = true)
    private String employeeNo;

    // duplicates gridRef
    @CSVColumn("Easting")
    private String easting;

    // duplicates gridRef
    @CSVColumn("Northing")
    private String northing;

    @CSVColumn("fieldmanager")
    private String fieldManager;

    //@CSVColumn(value = "Manager_EmployeeNo", ignored = true)
    //private String managerEmployeeNo;

    //@CSVColumn(value = "Manager_Auth", ignored = true)
    //private String managerAuth;

    @CSVColumn("Manager_Firstname")
    private String managerFirstname;

    @CSVColumn("Manager_Surname")
    private String managerSurname;

    //@CSVColumn(value = "Region", ignored = true)
    //private String region;

    //@CSVColumn(value = "regionName", ignored = true)
    //private String regionName;

    //@CSVColumn(value = "RM_EmployeeNo", ignored = true)
    //private String rmEmployeeNo;

    //@CSVColumn(value = "RM_Firstname", ignored = true)
    //private String rmFirstname;

    //@CSVColumn(value = "RM_Surname", ignored = true)
    //private String rmSurname;

//    public LegacyStaffIngest(CSVRecord record) {
//        this.timestamp = record.get("Transmission_Date");
//        this.auth = record.get("intnum");
//        //this.sex = (record.isSet("sex")) ? record.get("sex") : null;
//        this.title = record.get("Title");
//        this.firstname = record.get("firstname");
//        this.surname = record.get("Surname");
//        this.addressLine1 = record.get("add1");
//        this.addressLine2 = (record.isSet("add2")) ? record.get("add2") : null;
//        this.addressLine3 = (record.isSet("add3")) ? record.get("add3") : null;
//        this.addressLine4 = (record.isSet("add4")) ? record.get("add4") : null;
//        this.postcode = (record.isSet("postcode")) ? record.get("postcode") : null;
//        this.telNo = (record.isSet("tel")) ? record.get("tel") : null;
//        this.telNo2 = (record.isSet("tel2")) ? record.get("tel2") : null;
//        this.telNoMobile = (record.isSet("mob_tel")) ? record.get("mob_tel") : null;
//        this.telNoEmerg = (record.isSet("emerg_tel")) ? record.get("emerg_tel") : null;
//        this.email = record.get("email_add");
//        //this.gor = (record.isSet("GOR")) ? record.get("GOR") : null;
//        this.country = (record.isSet("country")) ? record.get("country") : null;
//        this.gridRef = (record.isSet("grid_ref")) ? record.get("grid_ref") : null;
//        //this.birthdate = (record.isSet("birthdate")) ? record.get("birthdate") : null;
//        //this.inactiveNo = (record.isSet("inactiveno")) ? record.get("inactiveno") : null;
//        this.employeeNo = record.get("EmployeeNo");
//        this.easting = (record.isSet("Easting")) ? record.get("Easting") : null;
//        this.northing = (record.isSet("Northing")) ? record.get("Northing") : null;
//        this.fieldManager = (record.isSet("fieldmanager")) ? record.get("fieldmanager") : null;
//        //this.managerEmployeeNo = (record.isSet("Manager_EmployeeNo")) ? record.get("Manager_EmployeeNo") : null;
//        //this.managerAuth = (record.isSet("Manager_Auth")) ? record.get("Manager_Auth") : null;
//        this.managerFirstname = (record.isSet("Manager_Firstname")) ? record.get("Manager_Firstname") : null;
//        this.managerSurname = (record.isSet("Manager_Surname")) ? record.get("Manager_Surname") : null;
//        //this.region = (record.isSet("Region")) ? record.get("Region") : null;
//        //this.regionName = (record.isSet("regionName")) ? record.get("regionName") : null;
//        //this.rmEmployeeNo = (record.isSet("RM_EmployeeNo")) ? record.get("RM_EmployeeNo") : null;
//        //this.rmFirstname = (record.isSet("RM_Firstname")) ? record.get("RM_Firstname") : null;
//        //this.rmSurname = (record.isSet("RM_Surname")) ? record.get("RM_Surname") : null;
//    }
}

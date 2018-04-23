package uk.gov.ons.fwmt.gateway.utility.readers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

@Slf4j
public class LegacyStaffReader extends CsvToBean<LegacyStaffEntity> {

    private static final String EMPLOYEENO = "employeeNo";
    private static final String INTNUM = "authNo";
    private static final String TITLE = "title";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    private static final String SEX = "sex";
    private static final String ADDRESS1 = "address1";
    private static final String ADDRESS2 = "address2";
    private static final String ADDRESS3 = "address3";
    private static final String ADDRESS4 = "address4";
    private static final String POSTCODE = "postcode";
    private static final String TEL = "tel";
    private static final String TEL2 = "tel2";
    private static final String MOBILE_TEL = "mobileTel";
    private static final String EMERGENCY_TEL = "emergencyTel";
    private static final String EMAIL = "email";
    private static final String GOR = "gor";
    private static final String COUNTY = "county";
    private static final String GRID_REF = "gridRef";
    private static final String MAINFIELDFORCE = "mainFieldForce";
    private static final String DATEOFBIRTH = "dateOfBirth";
    private static final String INACTIVENO = "inactiveNo";
    private static final String EASTING = "easting";
    private static final String NORTHING = "northing";
    private static final String FIELDMANAGER = "fieldManager";
    private static final String MANAGER_EMPLOYEENO = "managerEmployeeNo";
    private static final String MANAGER_AUTH = "managerAuth";
    private static final String MANAGER_FIRSTNAME = "managerFirstname";
    private static final String MANAGER_SURNAME = "managerSurname";
    private static final String REGION = "region";
    private static final String REGIONNAME = "regionName";
    private static final String RM_EMPLOYEENO = "rmEmployeeNo";
    private static final String RM_FIRSTNAME = "rmFirstname";
    private static final String RM_SURNAME = "rmSurname";
    private static final String[] NEW_USER_COLUMNS = new String[] { EMPLOYEENO, INTNUM, TITLE, FIRSTNAME, SURNAME, SEX,
            ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TEL, TEL2, MOBILE_TEL, EMERGENCY_TEL, EMAIL, GOR, COUNTY,
            GRID_REF, MAINFIELDFORCE, DATEOFBIRTH, INACTIVENO, EASTING, NORTHING, FIELDMANAGER, MANAGER_EMPLOYEENO,
            MANAGER_AUTH, MANAGER_FIRSTNAME, MANAGER_SURNAME, REGION, REGIONNAME, RM_EMPLOYEENO, RM_FIRSTNAME, RM_SURNAME };

    private CSVReader csvReader;
    private Iterator<String[]> csvIterator;
    private ColumnPositionMappingStrategy<LegacyStaffEntity> columnPositionMappingStrategy;

    public LegacyStaffReader(InputStream stream) {
        columnPositionMappingStrategy = new ColumnPositionMappingStrategy<>();
        columnPositionMappingStrategy.setType(LegacyStaffEntity.class);
        columnPositionMappingStrategy.setColumnMapping(NEW_USER_COLUMNS);
        csvReader = new CSVReader(new InputStreamReader(stream));
        csvIterator = csvReader.iterator();
        this.setCsvReader(csvReader);
        this.setMappingStrategy(columnPositionMappingStrategy);
    }
}

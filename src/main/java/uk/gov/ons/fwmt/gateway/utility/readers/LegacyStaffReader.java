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
	private static final String AUTHNO = "authNo";
	private static final String FORENAME = "forename";
	private static final String SURNAME = "surname";
	private static final String JOB_TITLE = "jobTitle";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String USER_TYPE = "userType";
	private static final String[] NEW_USER_COLUMNS = new String[] { EMPLOYEENO, AUTHNO, FORENAME, SURNAME, JOB_TITLE,
			EMAIL, PHONE, USER_TYPE };

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

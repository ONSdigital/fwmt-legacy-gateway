package uk.gov.ons.fwmt.gateway.utility.readers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

@Slf4j
public class LegacyStaffReader {

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

	CsvToBean<LegacyStaffEntity> csvToBean;

	public LegacyStaffReader(InputStream stream) {
		ColumnPositionMappingStrategy<LegacyStaffEntity> strategy = new ColumnPositionMappingStrategy<>();
		strategy.setType(LegacyStaffEntity.class);
		strategy.setColumnMapping(NEW_USER_COLUMNS);
		CsvToBeanBuilder<LegacyStaffEntity> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
		csvToBean = builder
				.withMappingStrategy(strategy)
				.withSkipLines(1)
				.build();
	}

	public Iterator<LegacyStaffEntity> iterator() {
		return csvToBean.iterator();
	}
}

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
import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;

@Slf4j
public class LegacyLeaversReader {

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

    CsvToBean<LegacyLeaverEntity> csvToBean;

    public LegacyLeaversReader(InputStream stream) {
        HeaderColumnNameMappingStrategy<LegacyLeaverEntity> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(LegacyLeaverEntity.class);
        CsvToBeanBuilder<LegacyLeaverEntity> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
        csvToBean = builder
                .withMappingStrategy(strategy)
                .build();
    }

    public Iterator<LegacyLeaverEntity> iterator() {
        return csvToBean.iterator();
    }
}

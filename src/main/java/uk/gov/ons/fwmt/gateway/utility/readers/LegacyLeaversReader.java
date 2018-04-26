package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

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
    private static final String[] NEW_USER_COLUMNS = new String[]{EMPLOYEENO, AUTHNO, FORENAME, SURNAME, JOB_TITLE,
            EMAIL, PHONE, USER_TYPE};

    private CsvToBean<LegacyLeaverEntity> csvToBean;

    public LegacyLeaversReader(InputStream stream) {
        ColumnPositionMappingStrategy<LegacyLeaverEntity> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(LegacyLeaverEntity.class);
        strategy.setColumnMapping(NEW_USER_COLUMNS);
        CsvToBeanBuilder<LegacyLeaverEntity> builder = new CsvToBeanBuilder<>(new InputStreamReader(stream));
        csvToBean = builder
                .withMappingStrategy(strategy)
                .withSkipLines(1)
                .build();
    }

    public Iterator<LegacyLeaverEntity> iterator() {
        return csvToBean.iterator();
    }
}

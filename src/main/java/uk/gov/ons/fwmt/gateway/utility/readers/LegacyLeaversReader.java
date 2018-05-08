package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class LegacyLeaversReader extends LegacyReaderBase<LegacyLeaverEntity> {
    private static final String[] CSV_HEADERS = {"employeeNo", "authNo", "forename", "surname", "jobTitle", "email",
        "phone", "userType"};

    public LegacyLeaversReader(InputStream stream) throws IOException {
        super(new InputStreamReader(stream));
    }

    @Override String[] getCSVHeaders() {
        return CSV_HEADERS;
    }

    @Override LegacyLeaverEntity parseRecord(CSVRecord record) {
        LegacyLeaverEntity entity = new LegacyLeaverEntity();
        entity.setEmployeeNo(record.get("employeeNo"));
        entity.setAuthNo(record.get("authNo"));
        entity.setForename(record.get("forename"));
        entity.setSurname(record.get("surname"));
        entity.setJobTitle(record.get("jobTitle"));
        entity.setEmail(record.get("email"));
        entity.setPhone(record.get("phone"));
        entity.setUserType(record.get("userType"));
        return entity;
    }
}

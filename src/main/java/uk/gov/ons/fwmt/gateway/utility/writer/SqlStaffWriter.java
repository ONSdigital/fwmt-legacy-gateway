package uk.gov.ons.fwmt.gateway.utility.writer;

import java.sql.Connection;


public class SqlStaffWriter {
  private static final String STAFF_CSV_FILE = "/Users/jacobharrison/fwmt-legacy-gateway/fwmt-legacy-gateway/src/main/resources/staff.csv"; // TODO make this work
  private static final String DATABASE_URL = "jdbc:postgresql://localhost/postgres";
  private static final String DATABASE_USERID = "postgres";
  private static final String DATABASE_PASSWORD = "postgres";

  public static void main(String[] args) {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection connection = databaseConnector.connectToDatabase(DATABASE_URL,DATABASE_USERID,DATABASE_PASSWORD);

    CSVWriter csvWriter = new CSVWriter();
    csvWriter.exportDataCopyManager(connection,STAFF_CSV_FILE);
  }
}


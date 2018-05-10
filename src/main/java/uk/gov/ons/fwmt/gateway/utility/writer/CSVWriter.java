package uk.gov.ons.fwmt.gateway.utility.writer;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CSVWriter {

  public void exportDataCopyManager(Connection connection, String filename){ // TODO separate from db functionality

    String psqlQuery = "SELECT * FROM gateway.staff";

    CopyManager copyManager = null;
    try {
      copyManager = new CopyManager((BaseConnection) connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    File file = new File(filename); //TODO better output directory
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      copyManager.copyOut("COPY (" + psqlQuery + ") TO STDOUT WITH HEADER CSV", fileOutputStream);
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  public void exportDataToCSV(Connection connection,String filename) { // TODO separate from db functionality
    Statement statement;
    String query;

    try {
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      query = "\\COPY (SELECT * FROM gateway.staff) TO '" + filename + "' delimiter ','"; //TODO fix this psql
      statement.executeQuery(query);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

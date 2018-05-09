package uk.gov.ons.fwmt.gateway.utility.writer;

import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseConnector {

  private final String databaseDriver = "org.postgresql.Driver";

  public Connection connectToDatabase(String db_connect_str, String db_userid, String db_password) {
    Connection connection;

    try {
      Class.forName(databaseDriver).newInstance();
      connection = DriverManager.getConnection(db_connect_str, db_userid, db_password);

    } catch(Exception e) {
      e.printStackTrace();
      connection = null;
    }
    return connection;
  }
}
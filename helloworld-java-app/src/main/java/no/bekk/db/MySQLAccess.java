package no.bekk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private ResultSet resultSet = null;

  public String sayHello() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connect = DriverManager.getConnection("jdbc:mysql://db.local/devops?user=devops&password=devops123");
      statement = connect.createStatement();
      resultSet = statement.executeQuery("select * from hello");
      String message = null;
      while (resultSet.next()) {
        message = resultSet.getString("MESSAGE");
        System.out.println("Retrieved " + message + " from database.");
      }
      return message;
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 

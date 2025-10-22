package edu.univ.erp.auth;

import java.sql.*;
import com.zaxxer.hikari.*;

/**
This is a class that provides a datasource from a connection pool managed by HikariCP.
The getDataSource method returns a DataSource object from the connection pool.
The static initialisation block is executed after the class is loaded.

This can significantly reduce the time required to repeatedly connect to the database to read and write data.
*/

public class HikariConnectionPool{
  public static final HikariDataSource sourceOfData;
  
  static {
    HikariConfig conf = new HikariConfig();
    conf.setJdbcUrl("jdbc:h2:../../../../db/Auth_DB");
    conf.setUsername("iiitd");
    conf.setPassword("student");
    sourceOfData =  new HikariDataSource(conf);
  }

  public static HikariDataSource getDataSource(){
    return sourceOfData;
  }
}

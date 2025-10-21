package edu.univ.erp.auth;

import java.sql.*;
import com.zaxxer.hikari.*;

public class HikariConnectionPool(){
  public static DataSource getDataSource(){
    HikariConfig conf = new HikariConfig();
    conf.setJdbcUrl("jdbc:h2:../../../../db/Auth_DB");
    conf.setUsername("iiitd");
    conf.password("student");

    return new HikariDataSource(conf);
  }  
}

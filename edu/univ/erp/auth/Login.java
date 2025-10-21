package edu.univ.erp.auth;
import exceptions.*;
import java.sql.*;
import hash.*;

/**
This is the Login class, which handles login.
The isValid method takes two parameters, username and password and checks if it is valid from the database
  throws: UserAccountNotFoundException when the username is not found in the database: 

@author 0xChaitanya
*/

public class Login{
  public static boolean isValid(String username, String password) throws UserAccountNotFoundException{
    Connection connect = HikariConnectionPool.getDataSource().getConnection();

    Statement statement = connect.createStatement();
    
    try{
      ResultSet result = statement.executeQuery(String.format("select password_hash from users_auth where username = %s", username));
      result.next();
      if (!BCrypt.checkpw(password, result.getString(1))){
        return false;
      }
      else{
        return true;
      }
    }
    catch (JdbcSQLNonTransientException e){
      throw new UserAccountNotFoundException();
    }
  }
}

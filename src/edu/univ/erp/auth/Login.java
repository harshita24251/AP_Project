package edu.univ.erp.auth;

import edu.univ.erp.exceptions.*;
import java.sql.*;
import edu.univ.erp.auth.hash.*;
import edu.univ.erp.util.Role;

/**
This is the Login class, which handles login.
The isValid method takes two parameters, username and password and checks if it is valid from the database
  throws: UserAccountNotFoundException when the username is not found in the database: 

@author 0xChaitanya
*/

public class Login{
  public static boolean isValid(String username, String password) throws UserAccountNotFoundException, SQLException{
    Connection connect = HikariConnectionPool.getDataSource().getConnection();

    Statement statement = connect.createStatement();
    
    try{
      ResultSet result = statement.executeQuery(String.format("select password_hash from users_auth where username = %s", username));
      ResultSet User_id = statement.executeQuery(String.format("select user_id from users_auth where username = %s", username));
      ResultSet role = statement.executeQuery(String.format("select role from users_auth where username = %s", username));

      result.next();
      if (!BCrypt.checkpw(password, result.getString(1))){
        return false;
      }
      else{
        Session.setCurrentUser_ID(User_id.getString(1));
        Session.setCurrentRole(Role.valueOf(role.getString(1)));
        return true;
      }
    }
    catch (SQLException e){
      throw new UserAccountNotFoundException();
    }
  }
}

package edu.univ.erp.data;

import edu.univ.erp.auth.hash.*;
import edu.univ.erp.auth.*;
import java.sql.*;
import java.lang.String;

public class Auth{
    public static String stringPassword;
    public static Boolean searchUsername(String Username) throws SQLException{
        Boolean tmp = false;
        try
            (
                Connection connect = edu.univ.erp.auth.HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select count(1) from users_auth where username = '%s'", Username));
            )
        {
            result.next();
            if (result.getInt(1) != 0) tmp = true;
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Auth\n"); //for prototyping may change later
            e.printStackTrace();
        }
        finally{
            return tmp;
        }
    }

    public static Boolean verifyPassword(String Username, char[] Password) throws  SQLException{
        Boolean tmp = false;
        stringPassword = String.copyValueOf(Password);
        try
            (
                Connection connect = edu.univ.erp.auth.HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select password_hash from users_auth where username = '%s'", Username));
            )
        {

            result.next();
            if (BCrypt.checkpw(stringPassword, result.getString(1))) tmp = true;
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Auth\n"); //for prototyping may change later
            e.printStackTrace();
        }
        return tmp;
    }

    public static String getRole(String Username) throws SQLException{
        String role = "";
        try
            (
                Connection connect = edu.univ.erp.auth.HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select role from users_auth where username = '%s'", Username));
            )
        {
            result.next();
            role = result.getString(1);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Auth\n"); //for prototyping may change later
            e.printStackTrace();
        }
        return role;
    }
}
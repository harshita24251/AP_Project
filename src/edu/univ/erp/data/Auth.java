package edu.univ.erp.data;

import edu.univ.erp.auth.hash.*;
import edu.univ.erp.auth.*;
import erp.randomIDGenerator;

import java.sql.*;
import java.lang.String;
import java.util.ArrayList;

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

    public static String getID(String Username) {
        String role = "";
        try
            (
                Connection connect = edu.univ.erp.auth.HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select user_id from users_auth where username = '%s'", Username));
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

    public static void insertDetails(ArrayList<String> arr){
        try{
            Connection connect = edu.univ.erp.auth.HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("insert into users_auth values ('%s', '%s', '%s', '%s')", arr.get(0), arr.get(1), arr.get(2), BCrypt.hashpw(arr.get(3), BCrypt.gensalt())));
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Auth\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}
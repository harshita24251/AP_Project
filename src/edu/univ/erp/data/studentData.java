package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class StudentData{
    private static Connection connect = HikariConnectionPool.getDataSource().getConnection();

    private static ResultSet runQuery(String query) throws SQLException{
        Statement statement = connect.createStatement();
        try{
            ResultSet result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Excpetion occured in file data\\")
        }
        return result;
    }

    public static String fetchRollNo() throws SQLException{
        return runQuery(String.format("select roll_no from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static String fetchProgram() throws SQLException{
        return runQuery(String.format("select program from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static int fetchYear() throws SQLException{
        return runQuery(String.format("select year_ from students where user_id = %s", Session.getCurrentUser_ID())).getInt(1);
    }

    public static String fetchEmailId() throws SQLException{
        return runQuery(String.format("select emain_id from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }
}

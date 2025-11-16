package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class StudentData{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        try{
            ResultSet result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Excpetion occured in file data/Student\n");
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
        return runQuery(String.format("select email_id from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }
}

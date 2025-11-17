package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;

public class Enrollment{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = null;
        try{
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Enrollments\n"); //for prototyping may change later
        }
        return result;
    }

    public static String getSection_ID() throws SQLException{
        ResultSet result = runQuery(String.format("select section_id from enrollments where student_id = %s", Session.getCurrentUser_ID()));
        return result.getString(1);
    }

    public static String getEnrollment_ID() throws SQLException{
        ResultSet result = runQuery(String.format("select enrollment_id from enrollments where student_id = %s", Session.getCurrentUser_ID()));
        return result.getString(1);
    }

    public static String getStatus() throws SQLException{
        ResultSet result = runQuery(String.format("select status from enrollments where student_id = %s", Session.getCurrentUser_ID()));
        return result.getString(1);
    }
}
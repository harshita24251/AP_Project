package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class Enrollment{
    private static Connection connect = HikariConnectionPool.getDataSource().getConnection();

    private static ResultSet runQuery(String query) throws SQLException{
        Statement statement = connect.createStatement();
        try{
            ResultSet result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data\\Enrollments\n") //for prototyping may change later
        }
        return result;
    }

    public static String getSection_ID() throws SQLException{
        Resultset result = runQuery("select section_id from enrollments where student_id = %s", Session.getCurrentUser_ID());
        return result.getString(1);
    }

    public static String getEnrollment_ID() throws SQLException{
        Resultset result = runQuery("select enrollment_id from enrollments where student_id = %s", Session.getCurrentUser_ID());
        return result.getString(1);
    }

    public static String getStatus() throws SQLException{
        Resultset result = runQuery("select status from enrollments where student_id = %s", Session.getCurrentUser_ID());
        return result.getString(1);
    }
}
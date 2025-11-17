package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;

public class Section{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = null;
        try{
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }
        return result;
    }

    public static String getCourse_ID() throws SQLException{
        ResultSet result = runQuery(String.format("select course_id from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getString(1); 
    }

    public static String getInstructor_ID() throws SQLException{
        ResultSet result = runQuery(String.format("select instructor_id from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getString(1); 
    }

    public static Date getDayTime() throws SQLException{
        ResultSet result = runQuery(String.format("select day_time from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getDate(1); 
    }

    public static String getRoom() throws SQLException{
        ResultSet result = runQuery(String.format("select room from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getString(1); 
    }

    public static int getCapacity() throws SQLException{
        ResultSet result = runQuery(String.format("select capacity from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getInt(1); 
    }

    public static int getSemester() throws SQLException{
        ResultSet result = runQuery(String.format("select semester from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getInt(1); 
    }

    public static int getYear() throws SQLException{
        ResultSet result = runQuery(String.format("select year_ from sections where section_id = %s", Enrollment.getSection_ID()));
        return result.getInt(1); 
    }
}
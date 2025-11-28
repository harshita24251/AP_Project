package edu.univ.erp.data;

import java.sql.*;
import java.util.ArrayList;

import edu.univ.erp.auth.*;
import erp.randomIDGenerator;

public class Section{
    private static Connection connect;

    // private static ResultSet runQuery(String query) throws SQLException{
    //     ResultSet result = null;
    //     try{
    //         connect = HikariConnectionPool.getDataSource().getConnection();
    //         Statement statement = connect.createStatement();
    //         result = statement.executeQuery(query);
    //     }
    //     catch(SQLException e){
    //         System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
    //     }
    //     return result;
    // }

    public static String getCourse_ID() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select course_id from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static String getInstructor_ID() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select instructor_id from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static Date getDayTime() throws SQLException{
        Date tmp = null;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select day_time from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getDate(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static String getRoom() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select room from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static int getCapacity() throws SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select capacity from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }
        
        return tmp;
    }

    public static int getSemester() throws SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select semester from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }
        
        return tmp;
    }

    public static int getYear() throws SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select year_ from sections where section_id = %s", Enrollment.getSection_ID()));
            )
        {
            while (result.next()){
                tmp = result.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }
        
        return tmp;
    }

    public static void insertDetails(ArrayList<String> arr){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("insert into sections values ('%s', '%s', '%s', '%s', %d, %d, %d, %f)", randomIDGenerator.generateID(), arr.get(0), arr.get(1), arr.get(2), arr.get(3), Integer.valueOf(arr.get(4)), Integer.valueOf(arr.get(5)), Integer.valueOf(arr.get(6)), Float.valueOf(arr.get(7))));

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Sections\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}

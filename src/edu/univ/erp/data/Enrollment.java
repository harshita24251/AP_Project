package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;

public class Enrollment{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        ResultSet result = null;
        try{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Enrollments\n"); //for prototyping may change later
        }
        return result;
    }

    public static String getSection_ID() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select section_id from enrollments where student_id = %s", Session.getCurrentUser_ID()));
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

    public static String getEnrollment_ID() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select enrollment_id from enrollments where student_id = %s", Session.getCurrentUser_ID()));
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

    public static String getStatus() throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select status from enrollments where student_id = %s", Session.getCurrentUser_ID()));
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

    public static void newEnrollment(){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("insert into enrollments values ('%s', '%s', '%s', '%s')", arr.get(7), Integer.valueOf(arr.get(0)), arr.get(1), Integer.valueOf(arr.get(2)), arr.get(3), arr.get(4), arr.get(6)));

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}

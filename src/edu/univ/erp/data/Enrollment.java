package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import erp.UUIDGenerator;

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

    public static void newEnrollment(String Section_id){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
//            UUIDGenerator.generate(arr.get(4))
            statement.executeUpdate(String.format("insert into enrollments values ('%s', '%s', '%s', '%s')", UUIDGenerator.generate(Section_id + "012"), Session.getCurrentUser_ID(), Section_id, "studying"));

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}

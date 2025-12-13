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
            ResultSet result = statement.executeQuery(String.format("select * from enrollments where section_id = '%s' and student_id = '%s'", Section_id, Session.getCurrentUser_ID()));

//            result.next();
            if (result.next() == false){ //for handling the case when registered twice
                statement.executeUpdate(String.format("insert into enrollments values ('%s', '%s', '%s', '%s')", UUIDGenerator.generate(Section_id + "012"), Session.getCurrentUser_ID(), Section_id, "studying"));
            }
            else{
                System.out.println("user tries to register twice, but can only register once, edited.");
            }

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }

    public static void deleteEnrollment(String sectionId) {
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
            )
        {
            statement.executeUpdate(String.format("delete from enrollments where student_id = '%s' and section_id = '%s';", Session.getCurrentUser_ID(), sectionId));
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while deleting enrollment\n");
        }
    }

    public static boolean isEnrolled(String sectionId) {
        boolean registered = false;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select 1 from enrollments where student_id = '%s' and section_id = '%s';", Session.getCurrentUser_ID(), sectionId));
            )
        {
            if (result.next()) {
                registered = true;
            }
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while checking registration status\n");
        }

        return registered;
    }

}

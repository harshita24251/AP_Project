package edu.univ.erp.data;

import java.sql.*;

/**
 * The reason that both enrollment_id and course_id are used to fetch scores and total scores is that two students may be enrolled in the same course so the course_id will be the same but enrollment_id cannot be same.
 * 
 */

public class Grades{
    private static Connection connect;

    // private static ResultSet runQuery(String query) throws SQLException{
    //     try
    //         (
    //             connect = HikariConnectionPool.getDataSource().getConnection();
    //             Statement statement = connect.createStatement();
    //             ResultSet result = statement.executeQuery(query);
    //         )
    //     {
    //         while (result.next()){

    //         }
    //     }
    //     catch(SQLException e){
    //         System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
    //     }
    //     return tmp;
    // }

    public static String getComponent() throws SQLException{
        String tmp = "";
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select component from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID()));
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

    public static int getScore() throws SQLException{
        int tmp = 0;
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select score from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID()));
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

    public static int getTotalScore() throws SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select total_score from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID()));
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
}
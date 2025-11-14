package edu.univ.erp.data;

import java.sql.*;

/**
 * The reason that both enrollment_id and course_id are used to fetch scores and total scores is that two students may be enrolled in the same course so the course_id will be the same but enrollment_id cannot be same.
 * 
 */

public class Grades{
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

    public static String getComponent() throws SQLException{
        Resultset result = runQuery("select component from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID());
        return result.getString(1);
    }

    public static int getScore() throws SQLException{
        Resultset result = runQuery("select score from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID());
        return result.getInt(1);
    }

    public static int getTotalScore() throws SQLException{
        Resultset result = runQuery("select total_score from grades where course_id = %s and enrollment_id = %s", Section.getCourse_ID(), Enrollment.getEnrollment_ID());
    }
}
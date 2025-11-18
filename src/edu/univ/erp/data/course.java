package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.HashMap;
import java.lang.Exception;

public class Course{
    private static Connection connect;

    public static String getTitle(String Course_ID) throws SQLException{
        String tmp = "";

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select title from courses where course_id = '%s'", Course_ID));
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

    public static int getCredits() throws SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select credits from courses where course_id = '%s'", Section.getCourse_ID()));
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

    public static HashMap<String, Integer> getCourseComponents(String Course_ID) throws SQLException{
        /**
         * Name, total_score
         */
        HashMap<String, Integer> tmp = new HashMap<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select distinct grades.component, grades.weightage from grades, enrollments join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s'", Session.getCurrentUser_ID(), Course_ID));
            )
        {
            while (result.next() != false){
                tmp.put(result.getString(1), result.getInt(2));
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }
}
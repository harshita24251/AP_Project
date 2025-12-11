package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.HashMap;
import java.util.ArrayList;
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
            System.out.println("Exception occured at data/Course\n"); //for prototyping may change later
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

    public static ArrayList<ArrayList<String>> getAllCourseDetails() throws SQLException{

        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery("select * from courses");
            )
        {
            while (result.next() != false){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(result.getString(1)); //course_id
                temp.add(result.getString(2)); //acronym
                temp.add(String.format("%d", result.getInt(3))); //credits
                temp.add(result.getString(4)); //title

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Course\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static ArrayList<String> getAllCourseDetails(String Course_ID) throws SQLException{

        ArrayList<String> tmp = new ArrayList<>();

        try
                (
                        Connection connect = HikariConnectionPool.getDataSource().getConnection();
                        Statement statement = connect.createStatement();
                        ResultSet result = statement.executeQuery(String.format("select * from courses where course_id = '%s'", Course_ID));
                )
        {
            while (result.next() != false){
                tmp.add(result.getString(1)); //course_id
                tmp.add(result.getString(2)); //acronym
                tmp.add(String.format("%d", result.getInt(3))); //credits
                tmp.add(result.getString(4)); //title

            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Course\n"); //for prototyping may change later
        }

        return tmp;
    }
}

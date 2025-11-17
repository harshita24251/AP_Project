package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.HashMap;

public class Course{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = null;
        try{
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Course\n"); //for prototyping may change later
        }
        return result;
    }

    public static String getTitle(String Course_ID) throws SQLException{
        ResultSet result = runQuery(String.format("select title from courses where course_id = '%s'", Course_ID));
        result.next();
        return result.getString(1); 
    }

    public static int getCredits() throws SQLException{
        ResultSet result = runQuery(String.format("select credits from courses where course_id = '%s'", Section.getCourse_ID()));
        return result.getInt(1); 
    }

    public static HashMap<String, Integer> getCourseComponents(String Course_ID) throws SQLException{
        /**
         * Name, total_score
         */
        String query = String.format("select distinct grades.component, grades.weightage from grades, enrollments join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s'", Session.getCurrentUser_ID(), Course_ID);

        HashMap<String, Integer> tmp = new HashMap<>(); 
        ResultSet result = runQuery(query);

        while (result.next() != false){
            tmp.put(result.getString(1), result.getInt(2));
        }

        return tmp;
    }
}
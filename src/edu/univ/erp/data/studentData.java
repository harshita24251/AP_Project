package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.HashMap;
import java.util.ArrayList;

public class StudentData{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = null;
        try{
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Excpetion occured in file data/Student\n");
        }
        return result;
    }

    public static String fetchRollNo() throws SQLException{
        return runQuery(String.format("select roll_no from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static String fetchProgram() throws SQLException{
        return runQuery(String.format("select program from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static int fetchYear() throws SQLException{
        return runQuery(String.format("select year_ from students where user_id = %s", Session.getCurrentUser_ID())).getInt(1);
    }

    public static String fetchEmailId() throws SQLException{
        return runQuery(String.format("select email_id from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static int getTotalSemesters() throws  SQLException{
        ResultSet result = runQuery("select MAX(semester) from sections");
        result.next();
        return result.getInt(1);
    }

    public static HashMap<Integer, String> getCGPAPerSemester() throws SQLException{
        /**
         * METHOD CURRENTLY NOT IN USE
         * 
         * Method to retrieve semester and its grade in a HashTable as it allows access in constant time
         */
        String SQLQuery = String.format("select sections.semester, grades.final_grade from enrollments" +  
        " join students on students.user_id = enrollments.student_id" +  
        " join sections on sections.section_id = enrollments.section_id"+ 
        " join grades on grades.section_id = sections.section_id" + 
        " where grades.component = 'Endsem' and enrollments.student_id = '%s'", Session.getCurrentUser_ID());

        ResultSet result = null;
        result = runQuery(SQLQuery);

        HashMap<Integer, String> temp = new HashMap<>();
        
        while (result.next() != false){
            temp.put(result.getInt(1), result.getString(2));
        }
        return temp;
    }

    public static ArrayList<String> getRegisteredCourseID(int semester) throws SQLException{
        String query = String.format("select distinct sections.course_id from enrollments, grades join students on students user_id = enrollments.student_id join sections on sections.section_id = grades.section_id where grades.component = 'Endsem' and students.user_id = '%s' and sections.semester = %d;", Session.getCurrentUser_ID(), semester);

        ArrayList<String> tmp = new ArrayList<>();
        ResultSet result = runQuery(query);

        while (result.next() != false){
            tmp.add(result.getString(1));
        }

        return tmp;
    }

    public static HashMap<String, Double> getCourseComponentGrades(String Course_ID) throws SQLException{
        /**
         * score
         */
        String query = String.format("select distinct grades.component, grades.score, grades.total_score, grade.weightage from grades, enrollments join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s';", Session.getCurrentUser_ID(), Course_ID);

        HashMap<String, Double> tmp = new HashMap<>();
        ResultSet result = runQuery(query);

        while (result.next() != false){
            Integer weightage = new Integer(result.getInt(4));
            Integer obtained_score = new Integer(result.getInt(2));
            Integer total_score = new Integer(result.getInt(3));
            tmp.put(result.getString(1), (obtained_score / total_score) * weightage);
        }

        return tmp;
    }
}

/**
 * select grades.final_grade from enrollments
join students on students.user_id = enrollments.student_id
join sections on sections.section_id = enrollments.section_id
join grades on grades.section_id = sections.section_id
where grades.component = 'Endsem'
 */
package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import org.h2.engine.User;

import java.util.HashMap;
import java.util.ArrayList;

public class StudentData{
    private static Connection connect;

    // private static ResultSet runQuery(String query) throws SQLException{
    //     ResultSet result = null;
    //     try{
    //         connect = HikariConnectionPool.getDataSource().getConnection();
    //         Statement statement = connect.createStatement();
    //         result = statement.executeQuery(query);
    //     }
    //     catch(SQLException e){
    //         e.printStackTrace();
    //         System.out.println("Excpetion occured in file data/Student\n");
    //     }
    //     return result;
    // }

    public static int fetchRollNo(String User_ID){
        int tmp = 0;
        try
            (
                    Connection connect = HikariConnectionPool.getDataSource().getConnection();
                    Statement statement = connect.createStatement();
                    ResultSet result = statement.executeQuery(String.format("select roll_no from students where user_id = '%s'", User_ID));
            )
        {
            while (result.next()){
                tmp = result.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }
        return tmp;
    }

    public static String fetchName(String User_ID){
        String tmp = "";
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select name from students where user_id = '%s'", User_ID));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }
        return tmp;
    }

    public static String fetchProgram(){
        String tmp = "";
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select program from students where user_id = '%s'", Session.getCurrentUser_ID()));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentDate\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static int fetchYear() {
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select year_ from students where user_id = '%s'", Session.getCurrentUser_ID()));
            )
        {
            while (result.next()){
                tmp = result.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }
        return tmp;
    }

    public static String fetchEmailId(String User_ID) {
        String tmp = "";
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select email_id from students where user_id = '%s'", User_ID));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentDate\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static int getTotalSemesters() throws  SQLException{
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery("select MAX(semester) from sections");
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

    public static HashMap<Integer, String> getCGPAPerSemester() throws SQLException{
        /**
         * METHOD CURRENTLY NOT IN USE
         * 
         * Method to retrieve semester and its grade in a HashTable as it allows access in constant time
         */
        HashMap<Integer, String> tmp = new HashMap<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select sections.semester, grades.final_grade from enrollments" +  
                " join students on students.user_id = enrollments.student_id" +  
                " join sections on sections.section_id = enrollments.section_id"+ 
                " join grades on grades.section_id = sections.section_id" + 
                " where grades.component = 'Endsem' and enrollments.student_id = '%s'", Session.getCurrentUser_ID()));
            )
        {
            while (result.next() != false){
                tmp.put(result.getInt(1), result.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static ArrayList<String> getRegisteredCourseID(int semester) throws SQLException{
        ArrayList<String> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select distinct sections.course_id from enrollments, grades join students on students.user_id = enrollments.student_id join sections on sections.section_id = grades.section_id where grades.component = 'Endsem' and students.user_id = '%s' and sections.semester = %d;", Session.getCurrentUser_ID(), semester));
            )
        {
            while (result.next() != false){
                tmp.add(result.getString(1));
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Section\n"); //for prototyping may change later
        }
        
        return tmp;
    }

    public static HashMap<String, Double> getCourseComponentGrades(String Course_ID, String Student_ID) throws SQLException{
        /**
         * score
         */
        
        HashMap<String, Double> tmp = new HashMap<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select distinct grades.component, grades.score, grades.total_score, grades.weightage from grades join enrollments on grades.enrollment_id = enrollments.enrollment_id and grades.section_id = enrollments.section_id join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s';\n", Student_ID, Course_ID)); //changed here
            )
        {
            while (result.next() != false){
                double marks_obtained = result.getInt(4) * result.getInt(2) / result.getInt(3);
                tmp.put(result.getString(1), marks_obtained);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }
        
        return tmp;
    }

    public static HashMap<String, ArrayList<Integer>> getCourseComponentScores(String Course_ID, String Student_ID) {
        /**
         * score
         */

        HashMap<String, ArrayList<Integer>> tmp = new HashMap<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select distinct grades.component, grades.score, grades.total_score from grades join enrollments on grades.enrollment_id = enrollments.enrollment_id and grades.section_id = enrollments.section_id join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s';\n", Student_ID, Course_ID)); //changed here
            )
        {
            while (result.next() != false){
//                double marks_obtained = result.getInt(2) * 1.0;
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(result.getInt(2));
                temp.add(result.getInt(3));
//                temp.put(result.getInt(1), result.getInt(2))
                tmp.put(result.getString(1), temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static void insertGrades(HashMap<String, Double> gradesList, String Course_ID, String Student_ID){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            for (String str : gradesList.keySet()){
                statement.executeUpdate(String.format("update grades set score = %f where component = '%s' and section_id in (select enrollments.section_id from enrollments join sections on enrollments.section_id = sections.section_id where enrollments.student_id = '%s' and sections.course_id = '%s') and enrollment_id in (select enrollment_id from enrollments join sections on enrollments.section_id = sections.section_id where enrollments.student_id = '%s' and sections.course_id = '%s');\n", gradesList.get(str), str, Student_ID, Course_ID, Student_ID, Course_ID));
            }

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}

/**
 * update grades set score = 69 where component = 'Midsem' and enrollment_id in (select enrollment_id from enrollments join sections on enrollments.section_id = sections.section_id where enrollments.student_id = 'A1' and sections.course_id = 'CSE101');
 */

/**
 * select grades.final_grade from enrollments
join students on students.user_id = enrollments.student_id
join sections on sections.section_id = enrollments.section_id
join grades on grades.section_id = sections.section_id
where grades.component = 'Endsem'
 */
package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import org.h2.engine.User;

import java.util.HashMap;
import java.util.ArrayList;
import erp.randomIDGenerator;

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

    public static String fetchContact(String User_ID){
        String tmp = "";
        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select contact from students where user_id = '%s'", User_ID));
            )
        {
            while (result.next()){
                tmp = result.getString(1);
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

    public static int fetchSemester() {
        int tmp = 0;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select semester from students where user_id = '%s'", Session.getCurrentUser_ID()));
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
                ResultSet result = statement.executeQuery(String.format("select semester from students where user_id = '%s'", Session.getCurrentUser_ID()));
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
                ResultSet result = statement.executeQuery(String.format("select distinct sections.course_id from enrollments join students on students.user_id = enrollments.student_id join sections on sections.section_id = enrollments.section_id join courses on courses.course_id = sections.course_id left join grades on grades.section_id = sections.section_id and grades.component = 'endsem' where students.user_id = '%s' and sections.semester = %d;", Session.getCurrentUser_ID(), semester));
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
                ResultSet result = statement.executeQuery(String.format("select distinct grades.component, grades.score, grades.total_score, grades.weightage from grades join enrollments on grades.enrollment_id = enrollments.enrollment_id join sections on sections.section_id = grades.section_id where enrollments.student_id = '%s' and sections.course_id = '%s';\n", Student_ID, Course_ID)); //changed here
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

    public static void insertDetails(ArrayList<String> arr){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("insert into students values ('%s', %d, '%s', %d, '%s', '%s', '%s')", arr.get(7), Integer.valueOf(arr.get(0)), arr.get(1), Integer.valueOf(arr.get(2)), arr.get(3), arr.get(4), arr.get(6)));

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, Double>  getCGPA(){
        HashMap<Integer, Double> tmp = new HashMap<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select sections.semester, avg(case final_grade when 'A' then 10 when 'B' then 9 when 'C' then 8 when 'D' then 7 when 'E' then 6 when 'F' then 0 end) from grades join enrollments on grades.enrollment_id = enrollments.enrollment_id join sections on grades.section_id = sections.section_id where grades.component = 'Endsem' and enrollments.student_id = '%s' group by sections.semester", Session.getCurrentUser_ID())); //changed here
            )
        {
            while (result.next() != false){
                tmp.put(result.getInt(1), result.getDouble(2));
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
        }

        return tmp;
    }

    public static ArrayList<HashMap<String, String>> getAvailableCourses(){
        ArrayList<HashMap<String, String>> tmp = new ArrayList<>();

        long year_ = 0;
        if (fetchSemester() % 2 == 1){
            year_ = Math.round(Math.floor((double) fetchRollNo(Session.getCurrentUser_ID()) / 1000)) + Math.round(Math.floor((double)fetchSemester()  - 1));
        }
        else{
            year_ = Math.round(Math.floor((double) fetchRollNo(Session.getCurrentUser_ID()) / 1000)) + Math.round(Math.floor((double)fetchSemester()  - 2));
        }

        System.out.println(year_);

        try
            (
                    Connection connect = HikariConnectionPool.getDataSource().getConnection();
                    Statement statement = connect.createStatement();
                    ResultSet result = statement.executeQuery(String.format("select distinct sections.section_id, sections.course_id, sections.start_date, sections.end_date, sections.instructor_id from sections, students where sections.year_ = %d and sections.semester = students.semester and students.user_id = '%s' and sections.section_id not in (select section_id from enrollments where student_id = '%s');\n", year_, Session.getCurrentUser_ID(), Session.getCurrentUser_ID())); //changed here
            )
        {
//            System.out.println(Math.round(Math.floor((double) fetchRollNo(Session.getCurrentUser_ID()) / 1000)) + Math.floor((double)fetchSemester()  / 2));
            int count = 0;
            while (result.next() != false){
                HashMap<String, String> temp = new HashMap<>();
                temp.put(" ", String.valueOf(count++)); //serial number
                temp.put("Section ID", result.getString(1)); //section_id
                temp.put("Course ID", result.getString(2)); // course_id
                temp.put("start_date", result.getString(3)); // instructor_id
                temp.put("end_date", result.getString(4)); // day_time
                temp.put("instructor_id", result.getString(5)); // room
//                temp.put("Capacity", String.valueOf(result.getInt(6))); // capacity
//                temp.put("Semester", String.valueOf(result.getInt(7))); // semester
//                temp.put("Year", String.valueOf(result.getInt(8))); // year
//                temp.put("Duration", String.valueOf(result.getDouble(9))); // duration

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static boolean isPresent(int roll){
        boolean check = false;

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select exists (select 1 from students where roll_no = %d)", roll)); //changed here
            )
        {
            result.next();
            check = result.getBoolean(1);

        }
        catch(SQLException e){
            System.out.println("Exception occured at data/StudentData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return check;
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

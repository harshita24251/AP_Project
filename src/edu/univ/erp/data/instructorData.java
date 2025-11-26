package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.ArrayList;
import edu.univ.erp.domain.*;

import javax.xml.transform.Result;

public class InstructorData{
    private static Connection connect;

    public static ArrayList<ArrayList<String>> getSectionDetails(){
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select * from sections where instructor_id = '%s'", Session.getCurrentUser_ID()));
            )
        {
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(result.getString(1)); //section_id
                temp.add(result.getString(2)); //course_id
                temp.add(result.getString(3)); //instructor_id
                temp.add(result.getTime(4).toString()); //day_time
                temp.add(result.getString(5)); //room 
                temp.add(String.valueOf(result.getInt(6))); // capacity
                temp.add(String.valueOf(result.getInt(7))); // semester
                temp.add(String.valueOf(result.getInt(8))); // year
                temp.add(String.valueOf(result.getFloat(9))); // duration

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    } 

    public static ArrayList<ArrayList<Object>> getMyCourseComponents(String Course_ID){
        /**
         * Pair<Integer, Integer> : marks obtained, total marks
         */
        ArrayList<ArrayList<Object>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select distinct on (grades.component) * from grades join sections on sections.section_id = grades.section_id where instructor_id = '%s' and sections.course_id = '%s'", Session.getCurrentUser_ID(), Course_ID));
            )
        {
            int count = 1;
            while (result.next()){
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(String.valueOf(count++));
                temp.add(result.getString(2)); //title
                temp.add(String.valueOf(result.getInt(4))); //total score
                temp.add(String.valueOf(result.getInt(7))); //weightage
                temp.add(String.valueOf(result.getTimestamp(8))); //start_date
                temp.add(String.valueOf(result.getTimestamp(9))); //end_date
                temp.add(String.valueOf(10)); //attachments
                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static ArrayList<ArrayList<Object>> getStudentsEnrolled(String Course_ID){
        ArrayList<ArrayList<Object>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select students.user_id, students.roll_no, students.name, students.program, students.semester, students.email_id from students join enrollments on students.user_id = enrollments.student_id join sections on enrollments.section_id = sections.section_id join courses on sections.course_id = courses.course_id where courses.course_id = '%s' and sections.instructor_id = '%s';\n", Course_ID, Session.getCurrentUser_ID()));
            )
        {
            int count = 1;
            while (result.next()){
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(String.valueOf(count++));
                temp.add(result.getString(1)); //student id
                temp.add(String.valueOf(result.getInt(2))); //roll no
                temp.add(result.getString(3)); //name
                temp.add(result.getString(4)); //program
                temp.add(String.valueOf(result.getInt(5))); //semester
                temp.add(result.getString(6)); //email
                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static void addNewAssessment(String Course_ID, String title, int maxMarks, int weightage, Timestamp start, Timestamp end){
        try{
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet isExists = statement.executeQuery(String.format("select * from grades join sections on grades.section_id = sections.section_id join instructors on instructors.user_id = sections.instructor_id where grades.component = '%s' and instructors.user_id = '%s' and sections.course_id = '%s';", title, Session.getCurrentUser_ID(), Course_ID));
                if (isExists.next() == false){
                    statement.executeUpdate(String.format("insert into grades select enrollments.enrollment_id, '%s', 0, %d, null, enrollments.section_id, %d, '%s', '%s', 'not found' from enrollments join sections on enrollments.section_id = sections.section_id where sections.instructor_id = '%s' and sections.course_id = '%s'", title, maxMarks, weightage, start, end, Session.getCurrentUser_ID(), Course_ID));
                }
                else{
                    System.out.println("Couldn't add, Assessments of this name already exists");
                }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }

    public static void removeAssessment(String Course_ID, String title){
        try{
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            ResultSet isExists = statement.executeQuery(String.format("select * from grades join sections on grades.section_id = sections.section_id join instructors on instructors.user_id = sections.instructor_id where grades.component = '%s' and instructors.user_id = '%s' and sections.course_id = '%s';", title, Session.getCurrentUser_ID(), Course_ID));
            if (isExists.next() == true){
                statement.executeUpdate(String.format("delete from grades where component = '%s' and section_id in (select section_id from sections where instructor_id = '%s' and course_id = '%s');", title, Session.getCurrentUser_ID(), Course_ID));
            }
            else{
                System.out.println("Couldn't remove, Assessment not found");
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n");
            e.printStackTrace();
        }
    }
}
package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.ArrayList;
import edu.univ.erp.domain.*;
import java.util.Date;

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

    public static ArrayList<ArrayList<String>> getMyCourseComponents(String Course_ID){
        /**
         * Pair<Integer, Integer> : marks obtained, total marks
         */
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(String.format("select * from grades join sections on sections.section_id = grades.section_id where instructor_id = '%s'", Session.getCurrentUser_ID()));
            )
        {
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(result.getString(2)); //title
                temp.add(String.valueOf(getInt(3))); //score obtained
                temp.add(String.valueOf(getInt(4))); //total score
                temp.add(result.getString(5)); //final_grade
                temp.add(String.valueOf(7)); //weightage
                temp.add(String.valueOf(8)); //start_date
                temp.add(String.valueOf(9)); //end_date
                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/InstructorData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }
}
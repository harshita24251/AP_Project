package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.ArrayList;
import edu.univ.erp.domain.*;

public class AdminData{
    private static Connection connect;

    public static ArrayList<ArrayList<String>> getAllStudents(){
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery("select * from students");
            )
        {
            int count = 1;
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(String.valueOf(count++)); //serial number
                temp.add(result.getString(1)); //user_id
                temp.add(result.getString(2)); // roll no
                temp.add(result.getString(3)); //program
                temp.add(String.valueOf(result.getInt(4))); //semester
                temp.add(result.getString(5)); //email id
                temp.add(result.getString(6)); // name

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static ArrayList<ArrayList<String>> getAllFaculty(){
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
            (
                Connection connect = HikariConnectionPool.getDataSource().getConnection();
                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery("select * from instructors");
                )
        {
            int count = 1;
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(String.valueOf(count++)); //serial number
                temp.add(result.getString(1)); //user_id
                temp.add(result.getString(2)); // Name
                temp.add(result.getString(3)); //Department
                temp.add(result.getString(4)); //Email

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static ArrayList<ArrayList<String>> getAllCourses(){
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
                (
                        Connection connect = HikariConnectionPool.getDataSource().getConnection();
                        Statement statement = connect.createStatement();
                        ResultSet result = statement.executeQuery("select * from Courses");
                )
        {
            int count = 1;
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(String.valueOf(count++)); //serial number
                temp.add(result.getString(1)); //course_id
                temp.add(result.getString(2)); // acronym
                temp.add(result.getString(3)); // credits
                temp.add(result.getString(4)); // title

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static ArrayList<ArrayList<String>> getAllSections(){
        ArrayList<ArrayList<String>> tmp = new ArrayList<>();

        try
                (
                        Connection connect = HikariConnectionPool.getDataSource().getConnection();
                        Statement statement = connect.createStatement();
                        ResultSet result = statement.executeQuery("select * from sections");
                )
        {
            int count = 1;
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(String.valueOf(count++)); //serial number
                temp.add(result.getString(1)); //section_id
                temp.add(result.getString(2)); // course_id
                temp.add(result.getString(3)); // instructor_id
                temp.add(result.getString(4)); // day_time
                temp.add(result.getString(5)); // room
                temp.add(String.valueOf(result.getInt(6))); // capacity
                temp.add(String.valueOf(result.getInt(7))); // semester
                temp.add(String.valueOf(result.getInt(8))); // year
                temp.add(String.valueOf(result.getDouble(9))); // duration

                tmp.add(temp);
            }
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }

        return tmp;
    }

    public static void insertSectionData(ArrayList<String> arr){
        try
        {
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("insert into sections values ('%s', '%s', '%s', '%s', '%s', %d, %d, %d, %f, '%s')", arr.get(9), arr.get(0).split(" : ")[0], arr.get(1).split(" : ")[0], arr.get(3).split(" - ")[0], arr.get(5), Integer.valueOf(arr.get(6)), Integer.valueOf(arr.get(7)), Integer.valueOf(arr.get(8)), Float.valueOf(arr.get(4)), arr.get(2)));
            // add here
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }

    public static void updateSectionDate(String Section_id, Timestamp start, Timestamp end){
        try
        {
            Connection connect = HikariConnectionPool.getDataSource().getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("update sections set start_date = '%s', end_date = '%s' where section_id = '%s'", start, end, Section_id));
            // add here
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/AdminData\n"); //for prototyping may change later
            e.printStackTrace();
        }
    }
}

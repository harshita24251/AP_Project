package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.ArrayList;


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
}
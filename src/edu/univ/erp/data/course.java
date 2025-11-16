package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class Course{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        try{
            ResultSet result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data/Course\n"); //for prototyping may change later
        }
        return result;
    }

    public static String getTitle() throws SQLException{
        ResultSet result = runQuery("select title from courses where course_id = %s", Section.getCourse_ID());
        return result.getString(1); 
    }

    public static int getCredits() throws SQLException{
        ResultSet result = runQuery("select credits from courses where course_id = %s", Section.getCourse_ID());
        return result.getInt(1); 
    }

    
}
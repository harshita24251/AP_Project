package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class Course{
    private static Connection connect = HikariConnectionPool.getDataSource().getConnection();

    private static ResultSet runQuery(String query) throws SQLException{
        Statement statement = connect.createStatement();
        try{
            ResultSet result = statement.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println("Exception occured at data\\Section\n") //for prototyping may change later
        }
        return result;
    }

    public static String getTitle() throws SQLException{
        Resultset result = runQuery("select title from courses where course_id = %s", Section.getCourse_ID());
        return result.getString(1); 
    }

    public static int getCredits() throws SQLException{
        Resultset result = runQuery("select credits from courses where course_id = %s", Section.getCourse_ID());
        return result.getInt(1); 
    }

    
}
package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth;

public class StudentData{
    private static Connection connect = HikariConnectionPool.getDataSource().getConnection();

    private ResultSet runQuery(String query){
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public String fetchRollNo(){
        return runQuery(String.format("select roll_no from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public String fetchProgram(String user_id){
        return runQuery(String.format("select program from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public int fetchYear(String user_id){
        return runQuery(String.format("select year_ from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public String fetchEmailId(String user_id){
        return runQuery(String.format("select emain_id from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }
}

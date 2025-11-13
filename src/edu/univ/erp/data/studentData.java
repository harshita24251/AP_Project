package edu.univ.erp.data;

import java.sql.*;

public class studentData{
    private static Connection connect = HikariConnectionPool.getDataSource().getConnection();

    private ResultSet runQuery(String query){
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public String getRollNo(String user_id){
        return runQuery(String.format("select roll_no from students where user_id = %s", user_id)).getString(1);
    }

    public String getProgram(String user_id){
        return runQuery(String.format("select program from students where user_id = %s", user_id)).getString(1);
    }

    public int getYear(String user_id){
        return runQuery(String.format("select year_ from students where user_id = %s", user_id)).getString(1);
    }

    public String getEmailId(String user_id){
        return runQuery(String.format("select emain_id from students where user_id = %s", user_id)).getString(1);
    }
}

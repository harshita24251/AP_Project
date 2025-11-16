package edu.univ.erp.data;

import java.sql.*;
import edu.univ.erp.auth.*;
import java.util.HashMap;

public class StudentData{
    private static Connection connect;

    private static ResultSet runQuery(String query) throws SQLException{
        connect = HikariConnectionPool.getDataSource().getConnection();
        Statement statement = connect.createStatement();
        ResultSet result = null;
        try{
            result = statement.executeQuery(query);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Excpetion occured in file data/Student\n");
        }
        return result;
    }

    public static String fetchRollNo() throws SQLException{
        return runQuery(String.format("select roll_no from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static String fetchProgram() throws SQLException{
        return runQuery(String.format("select program from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static int fetchYear() throws SQLException{
        return runQuery(String.format("select year_ from students where user_id = %s", Session.getCurrentUser_ID())).getInt(1);
    }

    public static String fetchEmailId() throws SQLException{
        return runQuery(String.format("select email_id from students where user_id = %s", Session.getCurrentUser_ID())).getString(1);
    }

    public static HashMap<Integer, String> getCGPAPerSemester() throws SQLException{
        /**
         * Method to retrieve semester and its grade in a HashTable as it allows access in constant time
         */
        String SQLQuery = String.format("select grades.final_grade,sections.semester from enrollments" +  
        " join students on students.user_id = enrollments.student_id" +  
        " join sections on sections.section_id = enrollments.section_id"+ 
        " join grades on grades.section_id = sections.section_id" + 
        " where grades.component = 'Endsem' and enrollments.student_id = '%s'", Session.getCurrentUser_ID());

        ResultSet result = runQuery(SQLQuery);
        HashMap<Integer, String> temp = new HashMap<>();

        while (result.next() != false){
            temp.put(result.getInt(1), result.getString(2));
        }
        return temp;
    }
}

/**
 * select grades.final_grade from enrollments
join students on students.user_id = enrollments.student_id
join sections on sections.section_id = enrollments.section_id
join grades on grades.section_id = sections.section_id
where grades.component = 'Endsem'
 */
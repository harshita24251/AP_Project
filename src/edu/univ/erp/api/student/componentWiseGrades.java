package  edu.univ.erp.api.student;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;

public class componentWiseGrades{
    public static HashMap<String, Double> fetch(String Course_ID){
        HashMap<String, Double> tmp = null;
        
        try{
            tmp = StudentData.getCourseComponentGrades(Course_ID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
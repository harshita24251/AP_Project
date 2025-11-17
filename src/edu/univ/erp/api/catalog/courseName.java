package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;

public class courseName{
    public static String fetch(String Course_ID){
        String tmp = null;
        
        try{
            tmp = Course.getTitle(Course_ID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
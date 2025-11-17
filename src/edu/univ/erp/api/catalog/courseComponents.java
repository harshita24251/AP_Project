package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;

public class courseComponents{
    public static HashMap<String, Integer> fetch(String Course_ID){
        HashMap<String, Integer> tmp = null;
        
        try{
            tmp = Course.getCourseComponents();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
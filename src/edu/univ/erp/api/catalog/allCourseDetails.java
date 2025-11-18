package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.ArrayList;
import java.sql.*;

public class allCourseDetails{
    public static ArrayList<ArrayList<String>> fetch(){
        ArrayList<ArrayList<String>> tmp = null;
        
        try{
            tmp = Course.getAllCourseDetails();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
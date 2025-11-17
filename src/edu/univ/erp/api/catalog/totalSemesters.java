package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;
/**
 * Class to return the semester and its sgpa and HashMap format
 */
public class totalSemesters{
    public static int fetch(){
        int tmp = 0;
        try{
            tmp = StudentData.getTotalSemesters();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
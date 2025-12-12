package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;// for catching the exception and not talking to db
/**
 * Class to return the semester and its sgpa and HashMap format
 */
public class totalSemesters{
    public static int fetch(){
        return StudentData.getTotalSemesters();
    }
}
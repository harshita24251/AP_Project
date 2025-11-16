package edu.univ.erp.api.catalog;

import edu.univ.erp.data.*;
import java.util.HashMap;
import java.sql.*;
/**
 * Class to return the semester and its sgpa and HashMap format
 */
public class CGPAPerSemester{
    public static HashMap<Integer, String> fetch() throws SQLException{
        return StudentData.getCGPAPerSemester();
    }
}
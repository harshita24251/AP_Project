package edu.univ.erp.api.student;

import edu.univ.erp.data.*;
import java.util.ArrayList;
import java.sql.*;

public class registeredCourses{
    public static ArrayList<String> fetch(int semester){
        ArrayList<String> tmp = null;

        try{
            tmp = StudentData.getRegisteredCourseID(semester);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }
}
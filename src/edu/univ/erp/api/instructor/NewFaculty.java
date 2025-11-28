package edu.univ.erp.api.instructor;

import edu.univ.erp.data.*;
import java.util.ArrayList;
import java.sql.*;

public class NewFaculty{
    public static void add(ArrayList<String> arr){
        InstructorData.insertDetails(arr);
    }
}
package edu.univ.erp.api.student;

import edu.univ.erp.data.*;
import java.util.ArrayList;
import java.sql.*;

public class NewStudent{
    public static void add(ArrayList<String> arr){
        StudentData.insertDetails(arr);
    }
}